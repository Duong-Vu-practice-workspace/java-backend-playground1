# dry run to see what would happen
kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group name \
    --topic hello-world \
    --reset-offsets \
    --shift-by -3 \
    --dry-run

kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group name \
    --topic hello-world
    --reset-offsets \
    --shift-by -3 \
    --execute
    