package info.ogorzalek.mosa.activities;

import info.ogorzalek.mosa.R;
import info.ogorzalek.mosa.general.Backend;
import info.ogorzalek.mosa.general.Routing;
import info.ogorzalek.mosa.models.TableList;
import info.ogorzalek.mosa.models.TableList.OnTableListListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private LinearLayout layout;
	
	private Backend backend;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        backend = Backend.instance(this);
        
        layout = (LinearLayout) this.findViewById(R.id.main_layout);
        
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		TableList.getTableList(backend, onTableListListener);
		
	}

	private void decorateLayout(TableList list) {
		
		layout.removeAllViews();
		
		for(final String tableName : list.tables) {
			Button b = new Button(MainActivity.this);
			b.setText(tableName);
			b.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent i = Routing.getTable(MainActivity.this, tableName);
					startActivity(i);
				}
			});
			layout.addView(b);
		}
		
	};
	
	OnTableListListener onTableListListener = new OnTableListListener() {
		
		public void onTableList(TableList list) {
			decorateLayout(list);
		}
		
		public void onError(Exception e) {
			
		}
	};
    
    
}
