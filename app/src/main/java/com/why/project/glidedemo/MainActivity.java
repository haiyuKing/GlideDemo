package com.why.project.glidedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.why.project.glidedemo.glide.transformations.BitmapCircleUtil;
import com.why.project.glidedemo.glide.transformations.GlideCircleTransform;
import com.why.project.glidedemo.glide.transformations.MyRoundCornersTransformation;
import com.why.project.glidedemo.glide.transformations.RoundCornersTransformation;

public class MainActivity extends AppCompatActivity {

	private Context mContext;

	private ImageView mImgBase;
	private ImageView mImgOverride;
	private ImageView mImgMyRound;
	private ImageView mImgRound;
	private ImageView mImgCircle;
	private ImageView mImgCircleUtil;

	private String imgUrl = "https://pic.cnblogs.com/avatar/93830/20170607145247.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		initViews();
		initDatas();
	}

	private void initViews() {
		mImgBase = findViewById(R.id.img_base);
		mImgOverride = findViewById(R.id.img_override);
		mImgMyRound = findViewById(R.id.img_myRound);
		mImgRound = findViewById(R.id.img_round);
		mImgCircle = findViewById(R.id.img_circle);
		mImgCircleUtil = findViewById(R.id.img_circleUtil);
	}

	private void initDatas() {

		glideBase();
		glideOverride();
		glideRound();
		glideRound2();
		glideRound3();
		glideRound4();
	}

	//Glide的基础使用
	private void glideBase() {
		Glide.with(mContext)
				.load(imgUrl)
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.fitCenter()
				//默认淡入淡出动画
				.crossFade()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.into(mImgBase);
	}

	//Glide重新改变图片大小
	private void glideOverride() {
		setColumnNumber(mContext,3);//计算宽度和高度值（1：1.5或者1:1）

		Glide.with(mContext)
				.load(imgUrl)
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.centerCrop()
				.override(imageWidthSize,imageHeightSize)
				//默认淡入淡出动画
				.crossFade()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.into(mImgOverride);
	}

	//用于计算图片的宽高值
	private int imageWidthSize;
	private int imageHeightSize;

	private void setColumnNumber(Context context, int columnNumber) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		int widthPixels = metrics.widthPixels;
		imageWidthSize = widthPixels / columnNumber;
		imageHeightSize = (int)(imageWidthSize * 1.5);//长方形样式，二选一
		//imageHeightSize = imageWidthSize;//正方形样式，二选一
	}

	//Glide的圆角效果
	private void glideRound() {
		//该方案不合适，因为图片尺寸有大有小
		Glide.with(mContext)
				.load(imgUrl)
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.centerCrop()
				//默认淡入淡出动画
				.crossFade()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.transform(new MyRoundCornersTransformation(mContext,dip2px(mContext,8),
						MyRoundCornersTransformation.CornerType.TOP))
				.into(mImgMyRound);
	}

	private void glideRound2() {
		Glide.with(mContext)
				.load(imgUrl)
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.centerCrop()
				//默认淡入淡出动画
				.crossFade()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.bitmapTransform(new RoundCornersTransformation(mContext,dip2px(mContext,8),
						RoundCornersTransformation.CornerType.TOP))
				.into(mImgRound);
	}

	private void glideRound3() {
		Glide.with(mContext)
				.load(imgUrl)
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.centerCrop()
				//默认淡入淡出动画
				.crossFade()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.transform(new GlideCircleTransform(mContext))
				.into(mImgCircle);
	}

	private void glideRound4() {
		Glide.with(mContext)
				.load(imgUrl)
				.asBitmap()
				//设置等待时的图片
				.placeholder(R.drawable.img_loading)
				//设置加载失败后的图片显示
				.error(R.drawable.img_error)
				.fitCenter()
				//缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
				.skipMemoryCache(false)
				//缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）【设置为ALL，因为打开设置对话框界面的时候还需要展现缩略图】
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				//设置图片加载的优先级
				.priority(Priority.HIGH)
				.into(new BitmapImageViewTarget(mImgCircleUtil){
					@Override
					protected void setResource(Bitmap resource) {
						super.setResource(resource);
						Bitmap result = new BitmapCircleUtil(dip2px(mContext,8),
								BitmapCircleUtil.CornerType.TOP).circleCrop(resource);
						mImgCircleUtil.setImageBitmap(result);
					}
				});
	}

	/**
	 * dp转px
	 * 16dp - 48px
	 * 17dp - 51px*/
	public static int dip2px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)((dpValue * scale) + 0.5f);
	}
}
