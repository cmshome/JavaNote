#!/bin/bash
THIS_DIR=`dirname $0`
THIS_DIR=`cd ${THIS_DIR}&&pwd`
#再获取应用根目录，根据根目录找conf和lib等
ROOT_DIR=`dirname ${THIS_DIR}`
LOG_PATH=${ROOT_DIR}/logs
LIB_PATH=${ROOT_DIR}/libs
CONF_PATH=${ROOT_DIR}/conf
mkdir -p ${LOG_PATH}
instanceLog=${LOG_PATH}/instance.log

cd ${ROOT_DIR}
JAVA_OPTS="-server -Xms2048M -Xmx2048M -Xmn64M -XX:+UseG1GC -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -Xverify:none -XX:+DisableExplicitGC -Djava.awt.headless=true"
exec java ${JAVA_OPTS} -Dfile.encoding=UTF-8 -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4j2LogDelegateFactory -Djava.ext.dirs=$JAVA_HOME/jre/lib/ext:${LIB_PATH} -Dlog4j.configurationFile=${CONF_PATH}/log4j.json  io.vertx.core.Launcher run com.fs.tool.elastic.main.Main  -conf conf/es.conf  -cluster -Dvertx.hazelcast.config=${CONF_PATH}/cluster.xml 1>>${instanceLog} 2>&1 &
