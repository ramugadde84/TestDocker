
#!/bin/sh
java -XX:HeapDumpPath=/heapdumps/custom_heap_dump.hprof -XX:+HeapDumpOnOutOfMemoryError -jar /test-docker-spring-boot.jar