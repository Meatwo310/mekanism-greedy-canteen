package net.meatwo310.greedycanteen.mdk.config;

interface ConfigElement {
    void bindTo(ConfigVisitor visitor);
}
