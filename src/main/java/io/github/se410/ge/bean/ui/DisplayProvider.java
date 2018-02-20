package io.github.se410.ge.bean.ui;

public interface DisplayProvider<T extends DisplayComponent> {

    void handle(T component);
}
