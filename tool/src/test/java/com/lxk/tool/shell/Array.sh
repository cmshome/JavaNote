#!/bin/bash

#要ps的关键字
psArray=(
  elasticsearch
  server.properties
  fileCC
  ezSC
  collector.cluster.xml
  stat.cluster.xml
  complete2es
  stat2es
  alarm2es
  baseline2es
  log2es
  ezs2es
  statical2es
  dataService
  ttm2kafka
  log2kafka
  probe2raw
  analyzer
  controlcenter
  tomcat7-login
  tomcat7-apm
  tomcat7-self
  tomcat7-customAPP
  notification-4.2.0-SNAPSHOT.jar
  jar_with_dependencies-4.0
  play.core.server.ProdServerStart
  /home/shaql/nacos
)

# 对应ps关键字的线程的名字
nameArray=(
  es
  kafka
  fileCC
  ezSC
  collector
  stat
  complete2es
  stat2es
  alarm2es
  baseline2es
  log2es
  ezs2es
  statical2es
  dataService
  ttm2kafka
  log2kafka
  probe2raw
  analyzer
  controlcenter
  login
  apm
  self
  app
  notification
  4.Xcreater
  kafka-manager
  nacos
)

n=${#psArray[*]}
existIndex=0
bbb=0
abc=0
declare -a notExist
echo "                     存在的进程如下"
for (( i=0; i < n; i++ ));
do
  pid=$(ps -ef|grep ${psArray[$i]}|grep -v "grep"|wc -l)
  PID=$(ps -ef|grep ${psArray[$i]}|grep -v "grep"|awk '{print$2}')
  if [ $pid -ne 0 ]
    then
      existIndex=$[$existIndex+1];
      abc=$existIndex
	    echo $abc:  ${nameArray[$i]} "的进程号为:"$PID
  else
    notExist[$bbb]=${nameArray[$i]}
    bbb=$bbb+1
  fi
done

echo ""
echo ""
echo "                     不存在的进程如下"
n=${#notExist[*]}
for (( i=0; i < n; i++ ));
do
  echo $[$i+1]:  ${notExist[$i]} "进程不存在。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。"
done



