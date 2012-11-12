package info.ogorzalek.mosa.general;



import info.ogorzalek.mosa.activities.TableActivity;
import android.content.Context;
import android.content.Intent;

public class Routing {

	public static Intent getTable(Context ctx, String name) {
		Intent intent = new Intent(ctx, TableActivity.class);
		intent.putExtra("table_name", name);
		return intent;
	}


}
