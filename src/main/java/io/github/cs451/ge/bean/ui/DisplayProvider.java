package io.github.cs451.ge.bean.ui;

public interface DisplayProvider<T extends DisplayComponent> {

    void handle(T component);
}
