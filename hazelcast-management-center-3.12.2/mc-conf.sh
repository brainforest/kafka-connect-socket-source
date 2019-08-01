#!/bin/sh

java -cp hazelcast-mancenter-3.12.2.war com.hazelcast.webmonitor.cli.MCConfCommandLine "$@"
