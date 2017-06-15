package com.android_broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;  
    /** 
     * Demo描述: 
     * 监听Wifi网络的变化并且获得当前信号强度 
     *  
     * 
     */  
    public class MainActivity extends Activity {  
       
        TextView text;
        private Context mContext; 
        private IntentFilter intentFilter;
        private broadcastReceiver mbroadcastReceiver;
        int rssi[] = new int[200];
        int count = 0;
        int cnt=100;
        long time0;
        long time1;
        long time;
        Time ti = new Time();
        int s = 0;
        @Override  
        protected void onCreate(Bundle savedInstanceState) {  
            super.onCreate(savedInstanceState);  
            setContentView(R.layout.activity_main);  
            //send = (Button)findViewById(R.id.send); 
            text = (TextView)findViewById(R.id.textView1);
            intentFilter = new IntentFilter(android.net.wifi.WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        	//intentFilter.addAction("android.net.wifi.WifiManager.SCAN_RESULTS_AVAILABLE_ACTION");
			mbroadcastReceiver = new broadcastReceiver();
            this.registerReceiver(mbroadcastReceiver, intentFilter);
            WifiManager wifiMan1=(WifiManager)this.getSystemService(Context.WIFI_SERVICE);
            
            wifiMan1.startScan();
            

        } 
    
@Override
		protected void onDestroy() {
			// TODO 自动生成的方法存根
			super.onDestroy();
			
		}


    class broadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context arg0, Intent arg1) {
       	mContext = arg0;
       	int newRssi = 0;
       	time0 = System.currentTimeMillis();
       	if(count<cnt){
       		
        s = ti.second;
        WifiManager wifiMan=(WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        newRssi = wifiMan.getConnectionInfo().getRssi();
        //Toast.makeText(arg0, ""+newRssi,400).show();
        text.append(newRssi+"  ");
        rssi[count] = newRssi;
        
        while(true){
        time1 = System.currentTimeMillis();
        time = time1 - time0;
        if(time>=2000)break;
        }
  
        wifiMan.startScan();
        count++;
        }
       	else{
       		unregisterReceiver(mbroadcastReceiver);
       	}
//       	else if(count == cnt){
//       		for(int i=0;i<cnt;i++){
//            	text.append(rssi[i]+"  ");}
//       	}
       	
    }
    }
}  
