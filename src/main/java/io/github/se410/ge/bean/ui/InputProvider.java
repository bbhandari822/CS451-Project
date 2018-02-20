package io.github.se410.ge.bean.ui;

public interface InputProvider<T extends InputComponent> {
    void handle(T inputComponent);
}
