package com.lxk.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 * 工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 打扑克牌的时候，揭一张牌，从后往前，看这个牌应该插在哪个位置
 *
 * @author LiXuekai on 2020/8/3
 */
public class InsertSort extends AbstractSort {

    @Test
    public void test() {

        int[] sort = sort(super.arr);

        System.out.println();
        System.out.println(Arrays.toString(sort));
    }


    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }
}
