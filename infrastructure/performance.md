## With caching
```
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.90ms    4.63ms  50.22ms   88.69%
    Req/Sec   190.00     88.42   550.00     61.00%
  Latency Distribution
     50%    4.39ms
     75%    6.91ms
     90%   11.10ms
     99%   25.01ms
  18976 requests in 10.07s, 11.67MB read
Requests/sec:   1884.59
Transfer/sec:      1.16MB
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.42ms    1.62ms  66.50ms   95.31%
    Req/Sec   430.34     67.21   525.00     82.00%
  Latency Distribution
     50%    2.13ms
     75%    2.53ms
     90%    3.23ms
     99%    7.02ms
  42916 requests in 10.02s, 26.46MB read
Requests/sec:   4282.15
Transfer/sec:      2.64MB
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.22ms  753.85us  26.34ms   90.77%
    Req/Sec   455.63     38.57   525.00     75.50%
  Latency Distribution
     50%    2.07ms
     75%    2.40ms
     90%    2.84ms
     99%    4.45ms
  45424 requests in 10.02s, 28.21MB read
Requests/sec:   4534.30
Transfer/sec:      2.82MB
```

## Without caching

```
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.34ms    5.79ms  63.96ms   88.04%
    Req/Sec   130.11     49.99   240.00     62.30%
  Latency Distribution
     50%    6.56ms
     75%   10.00ms
     90%   15.01ms
     99%   31.72ms
  12999 requests in 10.04s, 8.09MB read
Requests/sec:   1295.20
Transfer/sec:    825.12KB
-------------------------------------------------------------------------------------------
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.24ms    1.87ms  28.25ms   91.20%
    Req/Sec   325.77     76.32   454.00     62.60%
  Latency Distribution
     50%    2.70ms
     75%    3.50ms
     90%    4.85ms
     99%   11.44ms
  32494 requests in 10.02s, 19.97MB read
Requests/sec:   3241.64
Transfer/sec:      1.99MB
-------------------------------------------------------------------------------------------
root@DESKTOP-25F4ODK:~# wrk -d 10 -t 10 -c 10 --latency -s ./test.lua http://localhost:8080
Running 10s test @ http://localhost:8080
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.79ms    1.02ms  19.00ms   86.94%
    Req/Sec   362.76     45.89   464.00     62.80%
  Latency Distribution
     50%    2.56ms
     75%    3.08ms
     90%    3.79ms
     99%    6.67ms
  36185 requests in 10.03s, 22.29MB read
Requests/sec:   3609.21
Transfer/sec:      2.22MB
```