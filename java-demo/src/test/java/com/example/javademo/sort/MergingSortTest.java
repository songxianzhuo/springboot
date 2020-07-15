package com.example.javademo.sort;

import com.example.javademo.JavaDemoApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 归并排序 Merging Sort
 *
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/06/05
 **/
public class MergingSortTest extends JavaDemoApplicationTests {

    private Integer[] array1 = {6,10,25,3,33,90,60,100,56,20,2,5,8,9,1,7,894,125};


    /**
     * 归并排序 递归方式
     */
    @Test
    public void testAsc(){
        Integer[] array = array1;
        // 临时序列
        Integer[] result = new Integer[array.length];
        msort(array,result,0,array.length -1);
        Arrays.asList(result).stream().forEach(System.out::println);
    }


    /**
     * 归并排序 空间换时间
     * @param source 原序列
     * @param result 临时序列
     * @param left 开始下标
     * @param right 结束下标
     */
    private void msort(Integer[] source,Integer[] result,int left,int right){
        Integer[] temp = new Integer[source.length];
        if(left == right){
            result[left] = source[left];
        }else {
            // 求中间值
            int middle = (left + right) / 2;
            // 左边序列进行归并排序
            msort(source,temp,left,middle);
            // 右边序列进行归并排序
            msort(source,temp,middle+1,right);
            // 将两组子序列从大到小归并到临时序列result中
            merge(temp,result,left,middle,right);
        }
    }

    /**
     * 归并排序 占用空间资源少
     * @param source 原序列
     * @param temp 临时序列
     * @param left 开始下标
     * @param right 结束下标
     */
    private void msort2(Integer[] source,Integer[] temp,int left,int right){
        if(left < right){
            // 求中间值
            int middle = (left + right) / 2;
            // 左边序列进行归并排序
            msort2(source,temp,left,middle);
            // 右边序列进行归并排序
            msort2(source,temp,middle+1,right);
            // 将两组子序列从大到小归并到临时序列result中
            merge(source,temp,left,middle,right);
            // 将temp临时序列覆盖回源序列left到right中
            System.arraycopy(temp,left,source,left,right-left+1);
        }
    }

    /**
     * 归并
     * @param source 源序列
     * @param temp 临时序列
     * @param left 开始下标
     * @param middle 中间下标
     * @param right 结束下标
     */
    private void merge(Integer[] source,Integer[] temp,int left,int middle,int right){
        int k = left;
        int j = middle +1;
        int i = left;
        // 将左右两个子序列的元素按索引由低到高开始比较，较小者优先放入到临时序列中
        while (i <= middle && j <= right) {
            if(source[i] < source[j]){
                temp[k++] = source[i++];
            }else{
                temp[k++] = source[j++];
            }
        }
        // 如果左边序列没有遍历完，则直接将左边序列剩下的元素放入到临时序列中
        while (i <= middle) {
            temp[k++] = source[i++];
        }
        // 如果右边序列没有遍历完，则直接将右边序列剩下的元素放入到临时序列中
        while (j <= right) {
            temp[k++] = source[j++];
        }
    }

    /**
     * 归并排序 非递归方式
     */
    @Test
    public void testAsc2(){
        Integer[] array = array1;
        // 临时序列
        Integer[] temp = new Integer[array.length];
        int k = 1;
        while(k < array.length){
            mergePass(array,temp,k,array.length-1);
            k*=2;
            mergePass(temp,array,k,array.length-1);
            k*=2;
        }
        Arrays.asList(array).stream().forEach(System.out::println);
    }

    /**
     * 将source数组中相邻长度为s的子序列两两归并
     * @param source
     * @param temp 临时数组
     * @param s
     * @param n
     */
    private void mergePass(Integer[] source,Integer[] temp,int s,int n){
        int i = 0;
        // 确保从i开始到n至少有2s个元素，否则循环体内right会超出数组边界
        while(i+2*s-1 <= n){
            int left = i;
            int middle = i+s-1;
            int right = i+2*s-1;
            // 将source数组中相邻长度为s的子序列两两归并至临时表中；
            merge(source,temp,left,middle,right);
            i = i+2*s;
        }
        // 如果middel=i+s-1小于n，说明最后还是给你两个序列，右序列长度是n-middle<s个
        if(i+s-1 < n){
            int left = i;
            int middle = i+s-1;
            int right = n;
            merge(source,temp,left,middle,right);
        }else{
            while(i <= n){
                temp[i] = source[i];
                i++;
            }
        }
    }



    /**
     * 归并排序 Merging Sort
     *      原理：假设初始序列含有n个记录，可以看成是n个有序的子序列，每个子序列的长度为1，然后两两归并，得到|n/2|（|x|为不小于x的整数）个长度为2或1的有序序列；
     *      然后在两两归并，......，如此反复，直至得到一个长度为n的有序序列为止，这种排序方法称为2路归并排序
     * 递归方式
     *      时间复杂度：
     *          一次归并排序最坏情况时间复杂度为：O(n)：
     *          由完全二叉树性质，归并排序需要进行|logn|+1次，
     *          因此整个归并排序时间复杂度为：O(nlogn)
     *      空间复杂度：
     *          每一次归并都需要创建一个跟源数列相同大小的临时序列，时间复杂度为：O(1)
     *          由完全二叉树性质，归并排序需要进行|logn|+1次，时间复杂度为：O(logn);
     *          递归过程中使用的栈空间深度为：|logn| +1
     *          因此整个归并排序空间复杂度为：O(logn)
     * 非递归方式
     *      时间复杂度：
     *          mergePass方法的时间复杂度为：O(n);
     *          由完全二叉树性质，归并排序需要进行|logn|+1次，时间复杂度为：O(logn);
     *          因此时间复杂度：O(nlogn)
     *      空间复杂度：
     *          需要一个临时序列，复杂度为：O(1)；
     *          省略了递归用的栈空间
     *          因此，空间复杂度为：O(1)
     * 性能：
     *      归并排序是一种比较占内存、但效率高且稳定的算法
     */
}
