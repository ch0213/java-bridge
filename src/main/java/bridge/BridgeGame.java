package bridge;

import java.util.List;

public class BridgeGame {
    private Bridge bridge;

    private final int bridgeSize;

    private int attemptCount;

    public BridgeGame(BridgeMaker bridgeMaker, int bridgeSize) {
        this.bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));
        this.bridgeSize = bridgeSize;
        this.attemptCount = 1;
    }

    public void move(String moving) {
        if (bridge.isArrived()) {
            throw new IllegalStateException("[ERROR] 이미 도착했습니다.");
        }

        if (bridge.inProgress()) {
            bridge.visit(BlockPosition.from(moving));
            return;
        }

        throw new IllegalStateException("[ERROR] 게임이 종료되었습니다.");
    }

    public void retry(BridgeMaker bridgeMaker) {
        if (inProgress()) {
            throw new IllegalStateException("[ERROR] 게임이 진행중입니다.");
        }

        if (isSuccess()) {
            throw new IllegalStateException("[ERROR] 이미 성공했습니다.");
        }

        bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));
        attemptCount++;
    }

    public boolean inProgress() {
        return bridge.inProgress();
    }

    public boolean isSuccess() {
        return bridge.isArrived();
    }

    public List<Block> bridge() {
        return bridge.blocks();
    }

    public int attemptCount() {
        return attemptCount;
    }
}
