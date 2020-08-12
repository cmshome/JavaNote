package com.lxk.doc.tree;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Comparator;
import java.util.List;

/**
 * @author LiXuekai on 2020/8/5
 */
public class Main {
    public static void main(String[] args) {
        String path = "/Users/fang/Documents/intelliJ/lixuekai/JavaNote";
        path = "/Users/fang/Downloads/lxk";
        List<String> exceptDir = Lists.newArrayList("target", "out", "test", "package", "gradle", "resources", "build");
        final File generateFile = new File(path);
        final String generate = DirectoryTreeV1.create(generateFile)
                .setDeep(20)
                .setFileFilter(pathname -> (!(pathname.isHidden()
                        || exceptDir.contains(pathname.getName())
                )))
                .sort(Comparator.comparing(File::getName))
                .setFlattenFlag(true)
                /*.showLength()
                .showModify()
                .showPermission()
                .addAppendContent(new DirectoryTreeV1.AppendContent() {
                    @Override
                    public String appendContent(File file) {
                        return "[" + file.getPath() + "]";
                    }
                })*/
                .generate(generateFile);
        System.out.println(generate);

    }
}
