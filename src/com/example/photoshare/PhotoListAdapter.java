package com.example.photoshare;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.parse.PrsPhoto;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class PhotoListAdapter extends ParseQueryAdapter<PrsPhoto> {

	public PhotoListAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<PrsPhoto>() {
			public ParseQuery<PrsPhoto> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery query = new ParseQuery("PrsPhoto");
				return query;
			}
		});
	}

	@Override
	public View getItemView(PrsPhoto photo, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.photo_list_adapter, null);
		}

		super.getItemView(photo, v, parent);

		ParseImageView photoImage = (ParseImageView) v.findViewById(R.id.icon);
		ParseFile photoFile = photo.getParseFile("photo");
		if (photoFile != null) {
			photoImage.setParseFile(photoFile);
			photoImage.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					// nothing to do
				}
			});
		}
		return v;
	}

}
