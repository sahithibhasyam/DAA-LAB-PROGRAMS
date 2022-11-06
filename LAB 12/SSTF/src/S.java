// SSTF disk scheduling algorithm
class SstfScheduling
{
    //Implement SSTF disk scheduling algorithm
    public void sstf_disk_scheduling(int[] queue, int head, int n)
    {
        if (n <= 0)
        {
            return;
        }
        double seek_time = 0.0;
        double minimum = 0.0;
        //This are storing the information of seek sequence
        int[] skeek = new int[n + 1];
        //Create 2d array which is used to store distance and visited status
        int[][] auxiliary = new int[n][2];
        for (int i = 0; i < n; ++i)
        {
            // set initial distance
            auxiliary[i][0] = 0;
            // set the visiting element status
            auxiliary[i][1] = 0;
        }
        // Loop controlling variable
        int i = 0;
        int j = 0;
        int location = 0;
        for (i = 0; i < n; i++)
        {
            skeek[i] = head;
            // Find distance using head value
            for (j = 0; j < n; ++j)
            {
                auxiliary[j][0] = queue[j] - head;
                if (auxiliary[j][0] < 0)
                {
                    auxiliary[j][0] = -auxiliary[j][0];
                }
            }
            minimum = Integer.MAX_VALUE;
            location = -1;
            //Find the minimum element location that is not visited
            for (j = 0; j < n; ++j)
            {
                if (auxiliary[j][1] == 0 && auxiliary[j][0] <= minimum)
                {
                    // Get the new minimum distance element location
                    location = j;
                    minimum = auxiliary[j][0];
                }
            }
            // Update the visited status of new get element
            auxiliary[location][1] = 1;
            // Update head data into current track value
            head = queue[location];
            // Add current distance into seek
            seek_time += auxiliary[location][0];
        }
        if (head != 0)
        {
            // Add last skee info
            skeek[n] = head;
        }
        System.out.print("\n Seek Sequence : ");
        //Display given queue elements
        for (i = 0; i <= n; i++)
        {
            System.out.print(" " + skeek[i] + "");
        }
        //Display result
        System.out.print("\n Total Seek Time : " + seek_time);
        System.out.print("\n Average Seek Time : " + seek_time / n + "\n");
    }
    public static void main(String[] args)
    {
        SstfScheduling obj = new SstfScheduling();
        // Request queue elements
        int[] queue = {
                98,
                182,
                41,
                122,
                14,
                125,
                65,
                68
        };
        //Get the number of elements in request queue
        int n = queue.length;
        //Initial head position
        int head = 53;
        obj.sstf_disk_scheduling(queue, head, n);
    }
}