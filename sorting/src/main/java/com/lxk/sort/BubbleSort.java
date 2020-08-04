package com.lxk.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 冒泡排序
 * 冒泡排序第1次遍历后会将最大值放到最右边，这个最大值也是全局最大值。
 * 标准冒泡排序的每一次遍历都会比较全部的元素，虽然最右侧的值已经是最大值了。
 * 改进之后，每次遍历后的最大值，次大值，等等会固定在右侧，避免了重复比较。
 *
 * @author LiXuekai on 2020/8/3
 */
public class BubbleSort extends AbstractSort {

    @Test
    public void test() {
        int[] sort = sort0(super.arr);
        System.out.println("排序结果。。。。。。 " + Arrays.toString(sort));

        sort = sort1(arr);
        System.out.println("排序结果。。。。。。 " + Arrays.toString(sort));

        sort = sort2(arr);
        System.out.println("排序结果。。。。。。 " + Arrays.toString(sort));
    }

    /**
     * 最原始版本，一次不省的都循环了
     * T(n) = (n-1)*(n-1) = (n-1)²
     * f(n) = n²   O(f(n)) == o(n²)
     * 若有某个辅助函数f(n),使得当n趋近于无穷大时，T(n)  /   f(n)   的极限值为不等于零的常数，则称f(n)是T(n)的同数量级函数。
     * 记作：T(n)   =   O(f(n))， 称  O(f(n))  为算法的渐进时间复杂度，简称时间复杂度。
     */
    public int[] sort0(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            list.add(arr.length - 1);
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        System.out.println("循环次数 " + list.toString());
        return arr;
    }

    /**
     * 稍微改进版
     */
    public int[] sort1(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            list.add(arr.length - 1 - i);
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        System.out.println("循环次数 " + list.toString());
        return arr;
    }

    /**
     * 改进，减少循环次数
     * 里面一层循环在某次扫描中没有执行交换，则说明此时数组已经全部有序列，无需再扫描了。
     * 因此，增加一个标记，每次发生交换，就标记，如果某次循环完没有标记，则说明已经完成排序。
     */
    public int[] sort2(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            // 每次先重置为false
            boolean swap = false;
            list.add(arr.length - i - 1);

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swap = true;
                }
            }
            //如果上一次扫描没有发生交换，则说明数组已经全部有序，退出循环
            if (!swap) {
                break;
            }
        }
        System.out.println("循环次数 " + list.toString());

        return arr;
    }
}
