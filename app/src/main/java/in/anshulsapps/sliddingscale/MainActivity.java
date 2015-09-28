package in.anshulsapps.sliddingscale;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.view.GestureDetector.OnGestureListener;

public class MainActivity extends AppCompatActivity {



    public static String[] dataObjects = new String[100];



    TextView num;
    int eleWidth;
    HorizontalListView listview;
    int showCount = 10;
    Scroller sr;
    int finalColValue = 20;
    public int x = 0;
    public int y = 0;
    public int lastPos = 0;
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        eleWidth = (int) width / 19;

        count = dataObjects.length;

        for(int i = 0; i < count; i++){
            dataObjects[i] = i+1+"";
        }

        num = (TextView) findViewById(R.id.num);

        listview = (HorizontalListView) findViewById(R.id.listview);
        listview.setAdapter(mAdapter);


    }

    private BaseAdapter mAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return dataObjects.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
            TextView title = (TextView) retval.findViewById(R.id.title);
            ImageView img = (ImageView) retval.findViewById(R.id.image);
            LinearLayout ele_ll = (LinearLayout) retval.findViewById(R.id.ele_ll);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)  ele_ll.getLayoutParams();
            params.width = eleWidth;

            int pos = position + 1;

            if(pos == 1 || pos % 5 == 0){
                title.setText(dataObjects[position]);
                img.setImageResource(R.drawable.mid_scale);

            }else{
                title.setText("");
                img.setImageResource(R.drawable.small_scale);
            }
            System.out.println("lastPos "+lastPos+" pos = "+pos+" x = "+x+" y = "+y);
            if(pos == 1){
                num.setText("10");
                lastPos = 1;
            }else {


                if(y == 0){
                    num.setText("10");
                    lastPos = 20;
                }else if(pos > lastPos){
                    num.setText(dataObjects[(pos-1) - 10]);
                    lastPos = pos;
                }else{
                    int newPos = pos + 20;
                    if(newPos > count){
                        newPos = count;
                    }
                    num.setText(dataObjects[(newPos-1) - 10]);
                    lastPos = newPos;
                }
                y = listview.mNextX;
            }
            return retval;
        }

    };


}
