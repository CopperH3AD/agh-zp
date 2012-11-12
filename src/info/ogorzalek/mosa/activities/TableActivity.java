package info.ogorzalek.mosa.activities;

import java.util.List;

import info.ogorzalek.mosa.R;
import info.ogorzalek.mosa.general.Backend;
import info.ogorzalek.mosa.models.Record;
import info.ogorzalek.mosa.models.Retrive;
import info.ogorzalek.mosa.models.Retrive.OnRetriveListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TableActivity extends Activity {

	private String tableName;
	
	private TextView tableNameText;
	private LinearLayout records;
	
	private Backend backend;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        
        backend = Backend.instance(this);
        
        tableNameText = (TextView) this.findViewById(R.id.table_name);
        records = (LinearLayout) this.findViewById(R.id.table_records);
        
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		tableName = getIntent().getStringExtra("table_name");
		tableNameText.setText(tableName);
		
		
		Retrive.getTable(backend, onRetriveListener, tableName, 1349460704, 1349460711);
		
	}

	private void decorateRecords(Retrive retrive) {

	}
	
	OnRetriveListener onRetriveListener = new OnRetriveListener() {
		
		public void onRetrive(Retrive retrive) {
			decorateRecords(retrive);
			Toast.makeText(TableActivity.this, "ok", Toast.LENGTH_SHORT).show();
		}
		
		public void onError(Exception e) {
			Toast.makeText(TableActivity.this, "nie ok: " + e.toString(), Toast.LENGTH_SHORT).show();
			
		}
	};
    
    
}
