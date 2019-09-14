package com.lxk.jdk.collection;

/**
 * 学习HashMap 1.8源码中的put方法
 * 详细代码注释
 * <p>
 * @author lxk on 2017/4/20
 */
public class MyHashMap {

    /**
     * Implements Map.put and related methods
     *
     * @param hash         hash for key
     * @param key          the key
     * @param value        the value to put
     * @param onlyIfAbsent if true, don't change existing value(put 传 false)
     * @param evict        if false, the table is in creation mode.(put 传 true)
     * @return previous value, or null if none
     */
    //final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    //    Node<K,V>[] tab;  //缓存底层数组用,都是指向一个地址的引用
    //    Node<K,V> p;      //插入数组的桶i处的键值对节点
    //    int n;            //底层数组的长度
    //    int i;            //插入数组的桶的下标
    //    //刚开始table是null或空的时候，初始化个默认的table；为tab和n赋值，tab指向底层数组，n为底层数组的长度
    //    if ((tab = table) == null || (n = tab.length) == 0){
    //        n = (tab = resize()).length;
    //    }
    //    //(n - 1) & hash：根据hash值算出插入点在底层数组的桶的位置，即下标值；为i赋值
    //    //p = tab[i]：为p赋值。（不管碰撞与否，都已经赋值了）
    //    //如果在数组上，没有发生碰撞，即当前要插入的位置上之前没有插入过值，则直接在此位置插入要插入的键值对
    //    if ((p = tab[i = (n - 1) & hash]) == null){
    //        tab[i] = newNode(hash, key, value, null);//插入的节点的next属性是null
    //    } else {    //发生碰撞，即当前位置已经插入了值
    //        Node<K,V> e;    //中间变量吧，跟冒泡排序里面的那个中间变量似的，起到个值交换的作用
    //        K k;            //同上
    //        //hash值相同，key也相同，那么就是更新这个键值对的值。同 jdk 1.7
    //        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))){ //注意在这个if内【e != null】
    //            e = p;//这地方，e = p  他们两个都是指向数组下标为i的地方，在这if else if else结束之后，再把节点的值value给更新了
    //        } else if (p instanceof TreeNode){  //这个树方法可能会返回null。
    //            //jdk 1.8引入了红黑树来处理碰撞，上面判断p的类型已经是树结构了，
    //            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);//如果是，则走添加树的方法。
    //        } else {    //注意在这个else内，当为添加新节点时，【e == 】；更新某个节点时，就不是null
    //            for (int binCount = 0; ; ++binCount) {//还未形成树结构，还是jdk 1.7的链表结构
    //                //差别就是1.7:是头插法，后来的留在数组上，先来的链在尾上；1.8:是先来的就留在数组上，后来的链在尾上
    //                //判断p.next是否为空，同时为e赋值，若为空，则p.next指向新添加的节点，这是在链表长度小于7的时候
    //                if ((e = p.next) == null) { //给e赋值，同时判断p.next()也就是e是null，则下面的逻辑就是在这链表上添加新节点
    //                    //这个地方有个不好理解的地方：在判断条件里面，把e指向p.next，也就是说现在e=null而不是下下一行错误的理解。
    //                    //这也就解释了更新的时候，返回oldValue，新建的时候，是不在那地方返回的。
    //                    p.next = newNode(hash, key, value, null);//e = p.next,p.next指向新生成的节点，也就是说e指向新节点（错误）
    //                    //对于临界值的分析：
    //                    //假设此次是第六次，binCount == 6,不会进行树变，当前链表长度是7；下次循环。
    //                    //binCount == 7，条件成立，进行树变，以后再put到这个桶的位置的时候，这个else就不走了，走中间的那个数结构的分叉语句啦
    //                    //这个时候，长度为8的链表就变成了红黑树啦
    //                    if (binCount >= TREEIFY_THRESHOLD - 1){// -1 for 1st //TREEIFY_THRESHOLD == 8
    //                        treeifyBin(tab, hash);
    //                    }
    //                    break;//插入新值或进行树变后，跳出for循环。此时e未重定向，还是指向null，虽然后面p.next指向了新节点。
    //                    //但是，跟e没关系。
    //                }
    //                //如果在循环链表的时候，找到key相同的节点，那么就跳出循环，就走不到链表的尾上了。
    //                // e已经在上一步已经赋值了，且不为null,也会跳出for循环，会在下面更新value的值
    //                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
    //                    break;
    //                }
    //                //这个就是p.next也就是e不为空，然后，还没有key相同的情况出现，那就继续循环链表，
    //                // p指向p.next也就是e，继续循环，继续，e=p.next
    //                p = e;
    //                //直到p.next为空，添加新的节点；或者出现key相等，更新旧值的情况才跳出循环。
    //            }
    //        }
    //        //经过上面if else if else之后，e在新建节点的时候，为null；更新的时候，则被赋值。在树里面处理putTreeVal（）同样如此，
    //        if (e != null) { // existing mapping for key//老外说的对，就是只有更新的时候，才走这，才会直接return oldValue
    //            V oldValue = e.value;
    //            //onlyIfAbsent 这个在调用hashMap的put()的时候，一直是false，那么下面更新value是肯定执行的
    //            if (!onlyIfAbsent || oldValue == null){
    //                e.value = value;
    //            }
    //            afterNodeAccess(e);
    //            return oldValue;
    //        }
    //    }
    //    ++modCount;
    //    if (++size > threshold){
    //        resize();
    //    }
    //    afterNodeInsertion(evict);
    //    return null;
    //}
}
