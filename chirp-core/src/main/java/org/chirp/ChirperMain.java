package org.chirp;

import org.chirp.impl.ChirpReceiverImpl;

/**
 * Created with IntelliJ IDEA.
 * User: rahulm
 * Date: 16/07/14
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChirperMain {

    public static void main(String[] args){

        int service_type = 0;
        if(args.length > 1)
            service_type = new Integer(args[0]);
        System.out.println(service_type);


        switch (service_type){
            case 1:  {

                ChirpReceiver chirpReceiver = new ChirpReceiverImpl();
                Thread thread = new Thread(chirpReceiver);
                thread.setDaemon(true);

                thread.start();

                while(true){

                    try {

                        Thread.sleep(1000);

                    }catch (InterruptedException e){

                        chirpReceiver.stop();
                        break;

                    }

                }

            }
            default:{

                //new ChirpBroadcasterImpl().broadcast("Hello");
                System.out.println("broadcast complete") ;

            }




        }


    }



}
