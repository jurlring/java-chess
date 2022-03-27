package chess.state;

public class Ended implements State {

    @Override
    public State start() {
        return new Ended();
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final String[] commands) {
        return new Ended();
    }

    @Override
    public State status() {
        return new Ended();
    }

    @Override
    public boolean isEnded() {
        return true;
    }

}
