package hu.daniel.rozsa.logic;

public abstract class AbstractLogic {

    private GatewayFactory gatewayFactory;


    protected abstract GatewayFactory createGatewayFactory();

    public GatewayFactory getGatewayFactory() {
        if (gatewayFactory == null) {
            gatewayFactory = createGatewayFactory();
        }
        return gatewayFactory;
    }
}
