local current_count = redis.call('zcount', KEYS[1], ARGV[1], ARGV[2])
if current_count < tonumber(ARGV[3]) then
    redis.call('expire', KEYS[1], 10 * 60)
    return redis.call('zadd', KEYS[1], ARGV[2], ARGV[4])
else
    return 0
end