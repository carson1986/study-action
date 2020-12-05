1. 以守护进程的方式启动rabbit
rabbitmq-server.bat -detached

2. 新增root账户
rabbitmqctl.bat add_user root root

3. 给root账户设置权限
rabbitmqctl.bat set_permissions -p / root ".*" ".*" ".*"

4. 给root账户设置tag
rabbitmqctl.bat set_user_tags root administrator

5. 查看队列的消息数和未确认数量
rabbitmqctl list_queues name messages_ready messages_unacknowledged

