//package com.zhangds.io.frame;
//
//public class MainActivity extends AppCompatActivity {
//
//    private Button bt1, bt2;
//    private TextView view3;
//    //自定义广播接收器
//    private MessageBroadcastReceiver receiver;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//        receiver = new MessageBroadcastReceiver();
//        registBroadcast();
//    }
//
//    private void initView() {
//        bt1 = findViewById(R.id.view);
//        bt2 = findViewById(R.id.view2);
//        view3 = findViewById(R.id.textView3);
//
//        bt1.setOnClickListener(v -> SessionManage.getInstance().writeToServer("这是客户端发送的消息"));
//        bt2.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ConnectService.class);
//            startService(intent);
//        });
//    }
//
//    private void registBroadcast() {
//        IntentFilter filter = new IntentFilter("mina");
//        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
//    }
//
//    private void unregistBroadcast() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//    }
//    /**
//     * 接受数据并更新UI
//     */
//    private class MessageBroadcastReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            view3.setText(intent.getStringArrayExtra("Message").toString());
//        }
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        stopService(new Intent(this, ConnectService.class));
//        unregistBroadcast();
//    }
//}
