package com.example.clock;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	Button start, stop, halt;
	TextView text, state;

	public class ClockThread implements Runnable {

		double value = 0;
		boolean run = true;

		public void terminate() {
			run = false;
			value = 0;
		}

		public void restart() {
			run = true;
		}

		public void halt() {
			run = false;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				while (run) {

					long quick = System.currentTimeMillis();
					while ((System.currentTimeMillis() - quick) / 1000 < 1) {
					}
					if (run)
						value += 1.0;
					if (run)
						refresh(value);
				}
			}
		}

	}

	ClockThread b = new ClockThread();
	Thread a = new Thread(b);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		a.setName("Hey");
		setContentView(R.layout.fragment_main);

		state = (TextView) findViewById(R.id.textView2);
		text = (TextView) findViewById(R.id.textView1);
		stop = (Button) findViewById(R.id.buttonStop);
		start = (Button) findViewById(R.id.buttonStart);
		text.setText("" + 0.0);
		state.setText("Hey");
		halt = (Button) findViewById(R.id.button1);

		long initial = System.currentTimeMillis();
		System.out.print(initial);
		// while ((System.currentTimeMillis() - initial) / 1000 < 3) {
		// System.out.println((System.currentTimeMillis() - initial) / 1000);
		// }

		start.setOnClickListener(new OnClickListener() {

			@Override
			public synchronized void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (a.getName().equals("Hey")) {
					a.start();
					a.setName("Running");
					state.setText("Running");
				} else if (a.getName().equals("Nope")) {
					b.restart();
					a.setName("Running");
					state.setText("Running");

				} else if (a.getName().equals("Stopped")) {
					b.restart();
					a.setName("Running");
					state.setText("Running");

				}
			}
		});

		stop.setOnClickListener(new OnClickListener() {

			@Override
			public synchronized void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (a.getName().equals("Running")) {

					b.halt();
					// try {
					// a.wait();
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					//
					state.setText("Nope");
					a.setName("Nope");
				}

			}
		});

		halt.setOnClickListener(new OnClickListener() {

			@Override
			public synchronized void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!a.getName().equals("Hey")) {
					b.terminate();
					// try {
					// a.wait();
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

					text.setText("" + 0.0);
					a.setName("Stopped");
					state.setText("Stopped");
				}
			}
		});
		// if (savedInstanceState == null) {
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	// public static class PlaceholderFragment extends Fragment {
	//
	// public PlaceholderFragment() {
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_main, container,
	// false);
	// return rootView;
	// }

	public void refresh(final double value) {
		Thread th = new Thread() {
			public void run() {
				text.setText("" + value);

			}
		};
		runOnUiThread(th);

	}
	// }

}
