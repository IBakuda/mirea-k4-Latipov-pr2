import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class Main {

    //static String finalHash1 = "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad";
    //static String finalHash2 = "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b";
    //static String finalHash3 = "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f";

    //static String Hash;
    static Boolean isFind = false;
    static String recieved;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        class MyRunnable implements Runnable {
            Exchanger<String> exchanger;
            String message;
            private final String hash;
            private boolean doStop = false;
            private int start;
            private int end;
            private String name;
            public synchronized void doStop() {
                //System.out.println("Поток остановлен");
                this.doStop = true;
            }
            public MyRunnable(String hash, int start,int end, Exchanger<String>ex,String name){
                this.exchanger = ex;
                this.hash=hash;
                this.start=start;
                this.end=end;
                this.name=name;
            }
            private synchronized boolean keepRunning() {
                return this.doStop;
            }
            @Override
            public void run() {
                while(!keepRunning()) {
                    try {
                        message = bruteForce(hash,start,end);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        exchanger.exchange(message);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Thread.sleep(3L * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.doStop=true;
                    System.out.println("");
                }
            }
        }

        Exchanger<String> ex = new Exchanger<String>();
        Scanner in = new Scanner(System.in);
        int value=1;
        String hash;
        int quan=0;
        while (value!=0) {
            System.out.print("Выберите режим поиска\n" +
                    "1] однокональный режим\n" +
                    "2] двухканальный режим\n" +
                    "3] трёхканальный режим\n" +
                    "4] четырёхканальный режим\n" +
                    "5] пятиканальный режим\n" +
                    "Ввод: ");
            quan = in.nextInt();
            System.out.print("\n1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad\n" +
                    "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b\n" +
                    "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f\n" +
                    "Введите хэш для подбора: ");
            hash = in.next();

            if (quan == 1) {
                Thread Thread1 = new Thread(new MyRunnable(hash, 0, 25, ex, "thread1"));
                Thread1.start();
                System.out.println("\nПоиск начат, запуск потока\n");
                while (true) {
                    try {
                        recieved = ex.exchange(recieved);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!recieved.equals("")) {
                        Thread1.stop();
                        System.out.println("\nПоиск Окончен, поток отключен\n");
                        break;
                    }
                }
            }
            else if (quan == 2) {
                Thread Thread1 = new Thread(new MyRunnable(hash, 0, 12, ex,"thread1"));
                Thread Thread2 = new Thread(new MyRunnable(hash, 13, 25, ex,"thread2"));
                Thread1.start();
                Thread2.start();
                System.out.println("\nПоиск начат, запуск потоков\n");
                while (true) {
                    try {
                        recieved = ex.exchange(recieved);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!recieved.equals("")) {
                        Thread1.stop();
                        Thread2.stop();
                        System.out.println("\nПоиск Окончен, потоки отключены\n");
                        break;
                    }
                }
            }
            else if (quan == 3) {
                Thread Thread1 = new Thread(new MyRunnable(hash, 0, 7, ex,"thread1"));
                Thread Thread2 = new Thread(new MyRunnable(hash, 8, 15, ex,"thread2"));
                Thread Thread3 = new Thread(new MyRunnable(hash, 16, 25, ex,"thread3"));
                Thread1.start();
                Thread2.start();
                Thread3.start();
                System.out.println("\nПоиск начат, запуск потоков\n");
                while (true) {
                    try {
                        recieved = ex.exchange(recieved);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!recieved.equals("")) {
                        Thread1.stop();
                        Thread2.stop();
                        Thread3.stop();
                        System.out.println("\nПоиск Окончен, потоки отключены\n");
                        break;
                    }
                }
            }
            else if (quan == 4) {
                Thread Thread1 = new Thread(new MyRunnable(hash, 0, 3, ex,"thread1"));
                Thread Thread2 = new Thread(new MyRunnable(hash, 4, 7, ex,"thread2"));
                Thread Thread3 = new Thread(new MyRunnable(hash, 8, 16, ex,"thread3"));
                Thread Thread4 = new Thread(new MyRunnable(hash, 17, 25, ex,"thread4"));
                Thread1.start();
                Thread2.start();
                Thread3.start();
                Thread4.start();
                System.out.println("\nПоиск начат, запуск потоков\n");
                while (true) {
                    try {
                        recieved = ex.exchange(recieved);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!recieved.equals("")) {
                        Thread1.stop();
                        Thread2.stop();
                        Thread3.stop();
                        Thread4.stop();
                        System.out.println("\nПоиск Окончен, потоки отключены\n");
                        break;
                    }
                }
            }
            else if (quan == 5) {
                Thread Thread1 = new Thread(new MyRunnable(hash, 0, 4, ex,"thread1"));
                Thread Thread2 = new Thread(new MyRunnable(hash, 5, 9, ex,"thread2"));
                Thread Thread3 = new Thread(new MyRunnable(hash, 10, 15, ex,"thread3"));
                Thread Thread4 = new Thread(new MyRunnable(hash, 16, 20, ex,"thread4"));
                Thread Thread5 = new Thread(new MyRunnable(hash, 21, 25, ex,"thread5"));
                Thread1.start();
                Thread2.start();
                Thread3.start();
                Thread4.start();
                Thread5.start();
                System.out.println("\nПоиск начат, запуск потоков\n");
                while (true) {
                    try {
                        recieved = ex.exchange(recieved);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!recieved.equals("")) {
                        Thread1.stop();
                        Thread2.stop();
                        Thread3.stop();
                        Thread4.stop();
                        Thread5.stop();
                        System.out.println("\nПоиск Окончен, потоки отключены\n");
                        break;
                    }
                }
            }
        }
    }



    private static String bruteForce(String finalHash,int start,int end) throws NoSuchAlgorithmException {
        //System.out.println("Running");
        String pas1;
        char alpha[] =
                {
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                };
        long time = System.currentTimeMillis();
        while(true){
            for(int letter1=start; letter1<=end; letter1++){
                for(int letter2=0; letter2<=25; letter2++){
                    for(int letter3=0; letter3<=25; letter3++){
                        for(int letter4=0; letter4<=25; letter4++){
                            for(int letter5=0; letter5<=25; letter5++){

                                pas1 = new StringBuilder().append(alpha[letter1]).append(alpha[letter2]).append(alpha[letter3]).append(alpha[letter4]).append(alpha[letter5]).toString();

                                MessageDigest md = MessageDigest.getInstance("SHA-256");
                                byte[]hashInBytes = md.digest(pas1.getBytes(StandardCharsets.UTF_8));
                                StringBuilder hash = new StringBuilder();
                                for (byte b : hashInBytes) {
                                    hash.append(String.format("%02x", b));
                                }
                                if(finalHash.equals(hash.toString())){
                                    isFind = true;
                                    System.out.println("-------------------------------------------");
                                    System.out.println("Hash: "+hash.toString());
                                    System.out.println("Password: "+pas1);
                                    System.out.println(System.currentTimeMillis() - time + " ms");
                                    System.out.println("-------------------------------------------");
                                    return pas1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}