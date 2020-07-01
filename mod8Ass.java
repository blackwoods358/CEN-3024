import java.util.Random;

class Sums extends Thread {

    private int[] arr;

    private int low, high, partial;

    public Sums(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = Math.min(high, arr.length);

    }



    public int getPartialSum() {
        return partial;
    }


    public void run() {
        partial = sum(arr, low, high);

    }



    public static int sum(int[] arr) {

        return sum(arr, 0, arr.length);

    }



    // Single sum calc
    public static int sum(int[] arr, int low, int high) {
        int total = 0;
        for (int i = low; i < high; i++) {
            total += arr[i];
        }
        return total;
    }



    public static int parallelSum(int[] arr) {
        return parallelSum(arr, Runtime.getRuntime().availableProcessors());
    }




// multi sum calc
    public static int parallelSum(int[] arr, int threads)

    {

        int size = (int) Math.ceil(arr.length * 1.0 / threads);

        Sums[] sums = new Sums[threads];

        for (int i = 0; i < threads; i++) {

            sums[i] = new Sums(arr, i * size, (i + 1) * size);

            sums[i].start();

        }try {

            for (Sums sum : sums) {

                sum.join();
            }

        } catch (InterruptedException e) { }

        int total = 0;

        for (Sums sum : sums) {

            total += sum.getPartialSum(); }

        return total;

    }

}






public class mod8Ass{
    public static void main(String[]args){
        Random R = new Random();
        //creation of array and filling with random numbers()
        int[] arr = new int[200000000];
        for(int i = 0; i < arr.length; i++){
// filling array with only numbers 1-10 (plus one outside of bounds is so it is never at zero)
            arr[i] = R.nextInt(10)+1;
        }


        long start = System.currentTimeMillis();

        System.out.println(Sums.sum(arr));

        System.out.println("One -> " + (System.currentTimeMillis() - start));




        start = System.currentTimeMillis();

        System.out.println(Sums.parallelSum(arr));

        System.out.println("Multi -> " + (System.currentTimeMillis() - start));

    }

}


