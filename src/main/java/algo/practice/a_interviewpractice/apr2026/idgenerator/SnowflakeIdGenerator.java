package algo.practice.a_interviewpractice.apr2026.idgenerator;

/**
 *
 * Classic Snowflake is timestamp | datacenter | worker | sequence.
 * For 64‑bit signed long, we usually keep the sign bit 0 (non‑negative), so effectively we use 63 bits.
 * Example layout (tune these for your system):
 * 1 bit: unused (sign bit, always 0)
 * 41 bits: timestamp (ms since custom epoch)
 * 5 bits: datacenter ID (0–31)
 * 5 bits: worker ID (0–31)
 * 12 bits: sequence within the same millisecond (0–4095)
 *
 * Max Server nodes = 2^5x2^5 = 1024 workers
 * Per‑node throughput: up to 4096 IDs/ms ≈ 4M IDs/sec
 * 41‑bit timestamp: 2⁴¹ ms ≈ 69 years of capacity
 *
 */
public class SnowflakeIdGenerator {

    // Bit allocations
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    // Max values
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);      // 31
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS); // 31
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);       // 4095

    // Bit shifts
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    // Custom epoch: 2020-01-01T00:00:00Z in milliseconds
    private static final long EPOCH = 1577836800000L;

    private final long workerId;
    private final long datacenterId;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("worker Id out of range");
        }
        if (datacenterId < 0 || datacenterId > MAX_DATACENTER_ID) {
            throw new IllegalArgumentException("datacenter Id out of range");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long currentTimestamp = currentTime();

        if (currentTimestamp < lastTimestamp) {
            // Clock moved backwards; handle this
            currentTimestamp = handleClockBackwards(currentTimestamp);
        }

        if (currentTimestamp == lastTimestamp) {
            // Same millisecond: increment sequence
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0L) {
                // Sequence overflow in this millisecond, wait for next ms
                currentTimestamp = waitUntilNextMillis(lastTimestamp);
            }
        } else {
            // New millisecond: reset sequence
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        long timestampPart = (currentTimestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT;
        long datacenterPart = datacenterId << DATACENTER_ID_SHIFT;
        long workerPart = workerId << WORKER_ID_SHIFT;
        long sequencePart = sequence;

        return timestampPart | datacenterPart | workerPart | sequencePart;
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }

    private long waitUntilNextMillis(long lastTimestamp) {
        long ts = currentTime();
        while (ts <= lastTimestamp) {
            ts = currentTime();
        }
        return ts;
    }

    private long handleClockBackwards(long currentTimestamp) {
        long offset = lastTimestamp - currentTimestamp;

        // Strategy 1: wait up to some threshold
        if (offset <= 5) { // e.g., allow small rollback
            try {
                Thread.sleep(offset);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            long ts = currentTime();
            if (ts < lastTimestamp) {
                // still behind, throw
                throw new IllegalStateException(
                        "Clock moved backwards. Refusing to generate id for " + offset + " ms");
            }
            return ts;
        } else {
            // Strategy 2: fail fast on larger rollback
            throw new IllegalStateException(
                    "Clock moved backwards. Refusing to generate id for " + offset + " ms");
        }
    }
}

