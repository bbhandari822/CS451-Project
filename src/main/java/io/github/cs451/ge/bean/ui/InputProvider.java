package io.github.cs451.ge.bean.ui;

public interface InputProvider<T extends InputComponent> {
    void handle(T inputComponent);
}
