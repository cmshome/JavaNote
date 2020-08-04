package com.lxk.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序
 * 无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
 * 唯一的好处可能就是不占用额外的内存空间了吧
 *
 * @author LiXuekai on 2020/8/3
 */
public class SelectionSort extends AbstractSort {


    @Test
    public void test() {

        int[] sort = sort(super.arr);
        System.out.println();
        System.out.println(Arrays.toString(sort));
    }

    public int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    // 记录目前能找到的最小值元素的下标
                    minIndex = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != minIndex) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
            System.out.println(Arrays.toString(arr));

        }
        return arr;
    }
}
