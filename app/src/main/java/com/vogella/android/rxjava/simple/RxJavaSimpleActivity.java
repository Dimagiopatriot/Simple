package com.vogella.android.rxjava.simple;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaSimpleActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();
    public int value = 0;

    final Observable<Integer> serverDownLoadObservable =
            Observable.create(emitter -> {
                SystemClock.sleep(10000); // simulate delay
                emitter.onNext(5);
                emitter.onComplete();
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_simple);
        View view = findViewById(R.id.button);
        view.setOnClickListener(v -> {
            v.setEnabled(false); // disables the button until execution has finished

            Disposable subscribe = serverDownLoadObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(integer -> {
                        updateTheUserInterface(integer);  // this methods updates the ui

                        v.setEnabled(true);  // enables it again
                    });
            disposable.add(subscribe);
        });
    }

    private void updateTheUserInterface(int integer) {
        TextView view = findViewById(R.id.resultView);
        view.setText(String.valueOf(integer));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable!=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onClick(View v){
        Toast.makeText(this, "Still active " + value++, Toast.LENGTH_SHORT).show();
    }

}
