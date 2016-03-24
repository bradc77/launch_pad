package com.launch_pad.dixie.launch_pad_lists;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;

public class StateDetails extends Activity
{
	private String mName = "";
	private String mStateName = "";
	private WebView mWebView = null;
	private ProgressDialog mProgress = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.state_details);
		
		mWebView = (WebView) findViewById(R.id.web_view);
		
		Bundle bundle = getIntent().getExtras(); 
		if (bundle != null)
		{
			mName = bundle.getString("state_name");
		}

		mStateName = mName;

		mName = mName.replaceAll(" ", "_");
		mName = Character.toUpperCase(mName.charAt(0)) + (mName.length() > 1 ? mName.substring(1) : "");

		loadStateData("https://en.wikipedia.org/wiki/" + mName);
	}

	private void loadStateData(final String url)
	{
		setupProgress();

		new Thread(new Runnable()
		{
			public void run()
			{
				mWebView.loadUrl(url);

				mWebView.post(new Runnable()
				{
					public void run()
					{
						dismissProgress();
					}
				});
			}
		}).start();
	}

	private void setupProgress()
	{
		if (mProgress == null)
		{
			mProgress = new ProgressDialog(StateDetails.this);
		}

		mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgress.setTitle("Getting Information for " + mStateName);
		mProgress.setMessage("Getting state information from network");
		mProgress.show();
	}

	private void dismissProgress()
	{
		if (mProgress != null)
		{
			mProgress.cancel();
			mProgress = null;
		}
	}
}
