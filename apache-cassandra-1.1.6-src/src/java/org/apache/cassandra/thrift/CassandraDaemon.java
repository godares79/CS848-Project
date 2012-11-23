/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cassandra.thrift;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.apache.cassandra.service.AbstractCassandraDaemon;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cassandra.concurrent.JMXEnabledThreadPoolExecutor;
import org.apache.cassandra.concurrent.NamedThreadFactory;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import CJD.CJDInterface;

/**
 * This class supports two methods for creating a Cassandra node daemon,
 * invoking the class's main method, and using the jsvc wrapper from
 * commons-daemon, (for more information on using this class with the
 * jsvc wrapper, see the
 * <a href="http://commons.apache.org/daemon/jsvc.html">Commons Daemon</a>
 * documentation).
 */

public class CassandraDaemon extends org.apache.cassandra.service.AbstractCassandraDaemon
{
    protected static CassandraDaemon instance;
    //Added by David
    public static CJDInterface cjdClient;
    public static HashMap<String,Double> resourceScores;

    static
    {
        AbstractCassandraDaemon.initLog4j();
    }

    private static Logger logger = LoggerFactory.getLogger(CassandraDaemon.class);
    private final static String SYNC = "sync";
    private final static String ASYNC = "async";
    private final static String HSHA = "hsha";
    public final static List<String> rpc_server_types = Arrays.asList(SYNC, ASYNC, HSHA);
    private ThriftServer server;
    private CJDMonitor monitor;  //Added by David

    protected void startServer()
    {
        if (server == null)
        {
            server = new ThriftServer(listenAddr, listenPort);
            server.start();
            monitor = new CJDMonitor();
            monitor.start();
        }
    }

    protected void stopServer()
    {
        if (server != null)
        {
            server.stopServer();
            try
            {
                server.join();
            }
            catch (InterruptedException e)
            {
                logger.error("Interrupted while waiting thrift server to stop", e);
            }
            server = null;
        }
    }

    public static void stop(String[] args)
    {
        instance.stopServer();
        instance.deactivate();
    }

    public static void main(String[] args)
    {
        instance = new CassandraDaemon();
        instance.activate();
    }
    
    /**
     * CJD monitoring thread.
     */
    private static class CJDMonitor extends Thread {
    	public CJDMonitor() {
    		resourceScores = new HashMap<String,Double>();
    		while (true) {
    			
    			try {
    				resourceScores = new HashMap<String,Double>(cjdClient.GetAllScores());
    				logger.info(resourceScores.toString());
//					resourceScores.put("129.97.173.68", cjdClient.GetNodeScore("129.97.173.68"));
//					resourceScores.put("129.97.173.69", cjdClient.GetNodeScore("129.97.173.69"));
//					resourceScores.put("129.97.173.70", cjdClient.GetNodeScore("129.97.173.70"));
//					resourceScores.put("129.97.173.71", cjdClient.GetNodeScore("129.97.173.71"));
//					resourceScores.put("129.97.173.73", cjdClient.GetNodeScore("129.97.173.73"));
//					resourceScores.put("129.97.173.74", cjdClient.GetNodeScore("129.97.173.74"));
//					resourceScores.put("129.97.173.75", cjdClient.GetNodeScore("129.97.173.75"));
//					resourceScores.put("129.97.173.76", cjdClient.GetNodeScore("129.97.173.76"));
//					resourceScores.put("129.97.173.77", cjdClient.GetNodeScore("129.97.173.77"));
//					resourceScores.put("129.97.173.78", cjdClient.GetNodeScore("129.97.173.78"));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    }

    /**
     * Simple class to run the thrift connection accepting code in separate
     * thread of control.
     */
    private static class ThriftServer extends Thread
    {
        private TServer serverEngine;

        public ThriftServer(InetAddress listenAddr, int listenPort)
        {
            // now we start listening for clients
            final CassandraServer cassandraServer = new CassandraServer();
            Cassandra.Processor processor = new Cassandra.Processor(cassandraServer);

            // Transport
            logger.info(String.format("Binding thrift service to %s:%s", listenAddr, listenPort));

            // Protocol factory
            TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory(true, true, DatabaseDescriptor.getThriftMaxMessageLength());

            // Transport factory
            int tFramedTransportSize = DatabaseDescriptor.getThriftFramedTransportSize();
            TTransportFactory inTransportFactory = new TFramedTransport.Factory(tFramedTransportSize);
            TTransportFactory outTransportFactory = new TFramedTransport.Factory(tFramedTransportSize);
            logger.info("Using TFastFramedTransport with a max frame size of {} bytes.", tFramedTransportSize);

            if (DatabaseDescriptor.getRpcServerType().equalsIgnoreCase(SYNC))
            {
                TServerTransport serverTransport;
                try
                {
                    serverTransport = new TCustomServerSocket(new InetSocketAddress(listenAddr, listenPort),
                                                              DatabaseDescriptor.getRpcKeepAlive(),
                                                              DatabaseDescriptor.getRpcSendBufferSize(),
                                                              DatabaseDescriptor.getRpcRecvBufferSize());
                }
                catch (TTransportException e)
                {
                    throw new RuntimeException(String.format("Unable to create thrift socket to %s:%s", listenAddr, listenPort), e);
                }
                // ThreadPool Server and will be invocation per connection basis...
                TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport)
                                                                         .minWorkerThreads(DatabaseDescriptor.getRpcMinThreads())
                                                                         .maxWorkerThreads(DatabaseDescriptor.getRpcMaxThreads())
                                                                         .inputTransportFactory(inTransportFactory)
                                                                         .outputTransportFactory(outTransportFactory)
                                                                         .inputProtocolFactory(tProtocolFactory)
                                                                         .outputProtocolFactory(tProtocolFactory)
                                                                         .processor(processor);
                ExecutorService executorService = new CleaningThreadPool(cassandraServer.clientState, serverArgs.minWorkerThreads, serverArgs.maxWorkerThreads);
                serverEngine = new CustomTThreadPoolServer(serverArgs, executorService);
                logger.info(String.format("Using synchronous/threadpool thrift server on %s : %s", listenAddr, listenPort));
                
                //Want to set up the RMI client here
                try {
            		String url = new String("rmi://127.0.0.1:1099//CJD");
            		cjdClient = (CJDInterface) Naming.lookup(url);
            		logger.info("Connected to RMI Server.");
            		
            		logger.info("ugster01 score = " + cjdClient.GetNodeScore("129.97.173.68"));
            		logger.info("ugster02 score = " + cjdClient.GetNodeScore("129.97.173.69"));
            		logger.info("ugster03 score = " + cjdClient.GetNodeScore("129.97.173.70"));
            		logger.info("ugster04 score = " + cjdClient.GetNodeScore("129.97.173.71"));
            		logger.info("ugster06 score = " + cjdClient.GetNodeScore("129.97.173.73"));
            		logger.info("ugster07 score = " + cjdClient.GetNodeScore("129.97.173.74"));
            		logger.info("ugster08 score = " + cjdClient.GetNodeScore("129.97.173.75"));
            		logger.info("ugster09 score = " + cjdClient.GetNodeScore("129.97.173.76"));
            		logger.info("ugster10 score = " + cjdClient.GetNodeScore("129.97.173.77"));
            		logger.info("ugster11 score = " + cjdClient.GetNodeScore("129.97.173.78"));
        		} catch(Exception ex) {
            		ex.printStackTrace();
        		}
            }
            else
            {
                TNonblockingServerTransport serverTransport;
                try
                {
                    serverTransport = new TCustomNonblockingServerSocket(new InetSocketAddress(listenAddr, listenPort),
                                                                             DatabaseDescriptor.getRpcKeepAlive(),
                                                                             DatabaseDescriptor.getRpcSendBufferSize(),
                                                                             DatabaseDescriptor.getRpcRecvBufferSize());
                }
                catch (TTransportException e)
                {
                    throw new RuntimeException(String.format("Unable to create thrift socket to %s:%s", listenAddr, listenPort), e);
                }

                if (DatabaseDescriptor.getRpcServerType().equalsIgnoreCase(ASYNC))
                {
                    // This is single threaded hence the invocation will be all
                    // in one thread.
                    TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverTransport).inputTransportFactory(inTransportFactory)
                                                                                                     .outputTransportFactory(outTransportFactory)
                                                                                                     .inputProtocolFactory(tProtocolFactory)
                                                                                                     .outputProtocolFactory(tProtocolFactory)
                                                                                                     .processor(processor);
                    logger.info(String.format("Using non-blocking/asynchronous thrift server on %s : %s", listenAddr, listenPort));
                    serverEngine = new CustomTNonBlockingServer(serverArgs);
                }
                else if (DatabaseDescriptor.getRpcServerType().equalsIgnoreCase(HSHA))
                {
                    // This is NIO selector service but the invocation will be Multi-Threaded with the Executor service.
                    ExecutorService executorService = new JMXEnabledThreadPoolExecutor(DatabaseDescriptor.getRpcMinThreads(),
                                                                                       DatabaseDescriptor.getRpcMaxThreads(),
                                                                                       60L,
                                                                                       TimeUnit.SECONDS,
                                                                                       new SynchronousQueue<Runnable>(),
                                                                                       new NamedThreadFactory("RPC-Thread"), "RPC-THREAD-POOL");
                    TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverTransport).inputTransportFactory(inTransportFactory)
                                                                                       .outputTransportFactory(outTransportFactory)
                                                                                       .inputProtocolFactory(tProtocolFactory)
                                                                                       .outputProtocolFactory(tProtocolFactory)
                                                                                       .processor(processor);
                    logger.info(String.format("Using custom half-sync/half-async thrift server on %s : %s", listenAddr, listenPort));
                    // Check for available processors in the system which will be equal to the IO Threads.
                    serverEngine = new CustomTHsHaServer(serverArgs, executorService, Runtime.getRuntime().availableProcessors());
                }
            }
        }

        public void run()
        {
            logger.info("Listening for thrift clients...");
            serverEngine.serve();
        }

        public void stopServer()
        {
            logger.info("Stop listening to thrift clients");
            serverEngine.stop();
        }
    }
}
