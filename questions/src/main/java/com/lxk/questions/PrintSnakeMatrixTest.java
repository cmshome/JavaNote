package com.lxk.questions;

import java.util.Scanner;

/**
 * 打印螺旋矩阵【蛇形矩阵】
 *
 * @author lxk on 2017/4/17
 */
public class PrintSnakeMatrixTest {
    private int n; //
    private int a[][]; // 声明一个矩阵
    private int value = 1;// 矩阵里数字的值

    public PrintSnakeMatrixTest(int i) {
        this.n = i;
        a = new int[n][n];
    }

    // 计算第m层左上角的数字
    private int getcorner(int m) {
        int corner = 1;
        int o = n - 1;
        for (int i = 0; i < m - 1; ++i) {
            corner += 4 * o;
            o = o - 2;
        }
        return corner;
    }

    // 生成矩阵的每一层的每一边的数
    // s表示4个方向，分别取值1,2,3,4，表示4个不同的方向。
    // o表示这条边的起始值。
    // x表示第m层每条边的数字个数
    private void side(int s, int o, int x, int m) {
        int i = 0;
        int j = 0;
        switch (s) {
            case 1:
                i = m - 1;
                j = m - 1;
                for (int k = 0; k < x; ++k) {
                    a[i][j + k] = value;
                    ++value;
                }

                break;
            case 2:
                i = m - 1;
                j = m - 1 + x;
                for (int k = 0; k < x; ++k) {
                    a[i + k][j] = value;
                    ++value;
                }
                break;
            case 3:
                i = m - 1 + x;
                j = m - 1 + x;
                for (int k = 0; k < x; ++k) {
                    a[i][j - k] = value;
                    ++value;
                }
                break;
            case 4:
                i = m - 1 + x;
                j = m - 1;
                for (int k = 0; k < x; ++k) {
                    a[i - k][j] = value;
                    ++value;
                }
                break;
        }
    }

    // 生成蛇形矩阵的第m层
    private void shell(int m)// m表示第m层
    {
        int x = n - 1 - (m - 1) * 2; // x表示第m层每条边的数字个数
        int o = getcorner(m);
        int o1 = o;
        int o2 = o1 + x;
        int o3 = o2 + x;
        int o4 = o3 + x;
        // System.out.println(o4);

        side(1, o, x, m);
        side(2, o, x, m);
        side(3, o, x, m);
        side(4, o, x, m);
    }

    // 生成蛇形矩阵
    public void snakeMatrix() {
        int m = (n + 1) / 2;// 计算一共有多少层
        for (int i = 1; i <= m; ++i) {

            shell(i);
        }
        if (n % 2 == 1) {
            a[n / 2][n / 2] = n * n;
        }

    }

    // 打印矩阵
    public void print() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] < 10) {
                    System.out.print(a[i][j] + "  ");
                } else {
                    System.out.print(a[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        PrintSnakeMatrixTest my = new PrintSnakeMatrixTest(new Scanner(System.in).nextInt());  //利用Scanner获取控制台输入
        my.snakeMatrix();
        my.print();
    }

}

