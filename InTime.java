import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class InTime{
    int MaxP;
    //折半插入排序
    void sortArray(int[][] array,int l,int r) {
        if(l<r)
        {
            int partition = partition(array, l, r);
            sortArray(array,l,partition-1);
            sortArray(array,partition+1,r);
        }
    }

    int partition(int[][] array,int l,int r)
    {
        int[] temp = array[l];
        while(l<r)
        {
            while(array[r][0]>=temp[0]&&l<r)
                r--;
            array[l]=array[r];
            while(array[l][0]<temp[0]&&l<r)
                l++;
            array[r] = array[l];
        }
        array[l]=temp;
        return l;
    }

    //合并区间
    public int[][] merge(int[][] intervals) {
        int curNum;
        int i=0;
        int j=0;
        int max;
        List<Integer> list = new ArrayList<>();
        sortArray(intervals,0,intervals.length-1);
        while(i<intervals.length)
        {
            curNum=1;
            max = intervals[i][1];
            while(j<intervals.length-1&&max>=intervals[j+1][0])
            {
                if(intervals[j+1][1]>max)
                    max = intervals[j+1][1];
                j++;
                curNum++;
            }
            if(MaxP<curNum)
                MaxP =curNum;
            /*if(j<intervals.length)
            if(intervals[j][1]>max)
                max = intervals[j][1];*/
            list.add(intervals[i][0]);
            list.add((max));
            if(j==intervals.length)
                break;
            else {
                i=j+1;
                j=i;
            }
        }
        int[][] array = new int[list.size()/2][2];
        int k=0;
        int p=0;
        while(p<list.size()) {
            array[k][0]=list.get(p++);
            array[k][1]=list.get(p++);
            k++;
        }
        return  array;
    }
    @Test
    public  void Test()
    {
        int[][] time={{0,30},{5,10},{15,20},{3,18},{16,20}};
        System.out.println("输入的时间:");
        for(int i=0;i<time.length;i++)
        {
            System.out.print("["+time[i][0]+","+time[i][1]+"]"+" ");
        }
        System.out.println();
        merge(time);
        System.out.println("同时可以约到的人数:"+MaxP+"人");
    }
}
