package hu.daniel.rozsa.logic;

public interface OnCompleteResult<T> {

    void onSuccess(T result);
    void onError(String errorMsg);
}
