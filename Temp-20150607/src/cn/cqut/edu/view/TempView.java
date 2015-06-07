package cn.cqut.edu.view;

import cn.cqut.edu.temp_20150607.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TempView extends View {
	/**
	 * ����������Paint,���û���ָ����Path
	 */
	private Paint mOutterPaint = new Paint();
	/**
	 * ��¼�û����Ƶ�Path
	 */
	private Path mPath = new Path();
	/**
	 * �ڴ��д�����Canvas
	 */
	private Canvas mCanvas;
	/**
	 * mCanvas��������������
	 */
	private Bitmap mBitmap;

	private int mLastX;
	private int mLastY;

	public TempView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public TempView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TempView(Context context) {
		super(context);
		init();
	}

	private void init() {
		mPath = new Path();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mOutterPaint.setColor(Color.RED);
		mOutterPaint.setAntiAlias(true);
		mOutterPaint.setDither(true);
		mOutterPaint.setStyle(Paint.Style.STROKE);
		mOutterPaint.setStrokeJoin(Paint.Join.ROUND); // Բ��
		mOutterPaint.setStrokeCap(Paint.Cap.ROUND); // Բ��
		mOutterPaint.setStrokeWidth(20);

		mCanvas.drawColor(Color.parseColor("#c0c0c0"));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Bitmap mback = BitmapFactory.decodeResource(getResources(),
				R.drawable.arr);
		canvas.drawBitmap(mback, 0, 0, null);
		drawPath();
		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	/**
	 * ��������
	 */
	private void drawPath() {
		mOutterPaint
				.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
		mCanvas.drawPath(mPath, mOutterPaint);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastX = x;
			mLastY = y;
			mPath.moveTo(mLastX, mLastY);
			break;
		case MotionEvent.ACTION_MOVE:

			mPath.lineTo(x, y);

			mLastX = x;
			mLastY = y;
			break;
		}

		invalidate();
		return true;
	}

}
