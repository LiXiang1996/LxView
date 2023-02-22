//package com.example.lxview.lxtools
//
//import android.R
//import android.app.Activity
//import android.content.Context
//import android.content.res.Configuration
//import android.graphics.Color
//import android.graphics.Paint.Align
//import android.hardware.Camera
//import android.hardware.Camera.PreviewCallback
//import android.os.Bundle
//import android.os.Handler
//import android.os.Message
//import android.os.PowerManager
//import android.os.PowerManager.WakeLock
//import android.util.Log
//import android.view.SurfaceHolder
//import android.view.SurfaceView
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import android.widget.TextView
//import android.widget.Toast
//import org.achartengine.ChartFactory
//import org.achartengine.GraphicalView
//import org.achartengine.chart.PointStyle
//import org.achartengine.model.XYMultipleSeriesDataset
//import org.achartengine.model.XYSeries
//import org.achartengine.renderer.XYMultipleSeriesRenderer
//import org.achartengine.renderer.XYSeriesRenderer
//import java.util.*
//import java.util.concurrent.atomic.AtomicBoolean
//
///**
// * 程序的主入口
// * @author liuyazhuang
// */
//class HeartBeatActivity : Activity() {
//	//曲线
//	private val timer = Timer()
//
//	//Timer任务，与Timer配套使用
//	private var task: TimerTask? = null
//	private var handler: Handler? = null
//	private val title = "pulse"
//
//	// TODO: https://github.com/ddanny/achartengine.git需要这个官方库arr
//	private var series: XYSeries? = null
//	private var mDataset: XYMultipleSeriesDataset? = null
//	private var chart: GraphicalView? = null
//	private var renderer: XYMultipleSeriesRenderer? = null
//	private var context: Context? = null
//	private var addX = -1
//	var addY = 0.0
//	var xv = IntArray(300)
//	var yv = IntArray(300)
//	var hua = intArrayOf(9, 10, 11, 12, 13, 14, 13, 12, 11, 10, 9, 8, 7, 6, 7, 8, 9, 10, 11, 10, 10)
//
//	/**
//	 * 类型枚举
//	 * @author liuyazhuang
//	 */
//	enum class TYPE {
//		GREEN, RED
//	}
//
//	public override fun onCreate(savedInstanceState: Bundle?) {
//		super.onCreate(savedInstanceState)
//		setContentView(R.layout.activity_main)
//		initConfig()
//	}
//
//	/**
//	 * 初始化配置
//	 */
//	private fun initConfig() {        //曲线
//		context = applicationContext
//
//		//这里获得main界面上的布局，下面会把图表画在这个布局里面
//		val layout = findViewById<View>(R.id.linearLayout1) as LinearLayout
//
//		//这个类用来放置曲线上的所有点，是一个点的集合，根据这些点画出曲线
//		series = XYSeries(title)
//
//		//创建一个数据集的实例，这个数据集将被用来创建图表
//		mDataset = XYMultipleSeriesDataset()
//
//		//将点集添加到这个数据集中
//		mDataset.addSeries(series)
//
//		//以下都是曲线的样式和属性等等的设置，renderer相当于一个用来给图表做渲染的句柄
//		val color = Color.GREEN
//		val style: PointStyle = PointStyle.CIRCLE
//		renderer = buildRenderer(color, style, true)
//
//		//设置好图表的样式
//		setChartSettings(renderer, "X", "Y", 0.0, 300.0, 4.0, 16.0, Color.WHITE, Color.WHITE)
//
//		//生成图表
//		chart = ChartFactory.getLineChartView(context, mDataset, renderer)
//
//		//将图表添加到布局中去
//		layout.addView(chart, ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT))
//
//		//这里的Handler实例将配合下面的Timer实例，完成定时更新图表的功能
//		handler = object : Handler() {
//			override fun handleMessage(msg: Message) {                //        		刷新图表
//				updateChart()
//				super.handleMessage(msg)
//			}
//		}
//		task = object : TimerTask() {
//			override fun run() {
//				val message = Message()
//				message.what = 1
//				handler.sendMessage(message)
//			}
//		}
//		timer.schedule(task, 1, 20) //曲线
//		//获取SurfaceView控件
//		preview = findViewById<View>(R.id.preview) as SurfaceView
//		previewHolder = preview!!.holder
//		previewHolder.addCallback(surfaceCallback)
//		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)        //		image = findViewById(R.id.image);
//		text = findViewById<View>(R.id.text) as TextView
//		text1 = findViewById<View>(R.id.text1) as TextView
//		text2 = findViewById<View>(R.id.text2) as TextView
//		val pm = getSystemService(POWER_SERVICE) as PowerManager
//		wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen")
//	}
//
//	//	曲线
//	public override fun onDestroy() {        //当结束程序时关掉Timer
//		timer.cancel()
//		super.onDestroy()
//	}
//
//	/**
//	 * 创建图表
//	 * @param color
//	 * @param style
//	 * @param fill
//	 * @return
//	 */
//	protected fun buildRenderer(color: Int, style: PointStyle?, fill: Boolean): XYMultipleSeriesRenderer {
//		val renderer = XYMultipleSeriesRenderer()
//
//		//设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
//		val r = XYSeriesRenderer()
//		r.setColor(Color.RED) //		r.setPointStyle(null);
//		//		r.setFillPoints(fill);
//		r.setLineWidth(1)
//		renderer.addSeriesRenderer(r)
//		return renderer
//	}
//
//	/**
//	 * 设置图标的样式
//	 * @param renderer
//	 * @param xTitle：x标题
//	 * @param yTitle：y标题
//	 * @param xMin：x最小长度
//	 * @param xMax：x最大长度
//	 * @param yMin:y最小长度
//	 * @param yMax：y最大长度
//	 * @param axesColor：颜色
//	 * @param labelsColor：标签
//	 */
//	protected fun setChartSettings(renderer: XYMultipleSeriesRenderer?, xTitle: String?, yTitle: String?, xMin: Double, xMax: Double, yMin: Double, yMax: Double, axesColor: Int, labelsColor: Int) {        //有关对图表的渲染可参看api文档
//		renderer.setChartTitle(title)
//		renderer.setXTitle(xTitle)
//		renderer.setYTitle(yTitle)
//		renderer.setXAxisMin(xMin)
//		renderer.setXAxisMax(xMax)
//		renderer.setYAxisMin(yMin)
//		renderer.setYAxisMax(yMax)
//		renderer.setAxesColor(axesColor)
//		renderer.setLabelsColor(labelsColor)
//		renderer.setShowGrid(true)
//		renderer.setGridColor(Color.GREEN)
//		renderer.setXLabels(20)
//		renderer.setYLabels(10)
//		renderer.setXTitle("Time")
//		renderer.setYTitle("mmHg")
//		renderer.setYLabelsAlign(Align.RIGHT)
//		renderer.setPointSize(3f)
//		renderer.setShowLegend(false)
//	}
//
//	/**
//	 * 更新图标信息
//	 */
//	private fun updateChart() {
//
//		//设置好下一个需要增加的节点
//		if (flag == 1.0) addY = 10.0 else { //			addY=250;
//			flag = 1.0
//			if (gx < 200) {
//				if (hua[20] > 1) {
//					Toast.makeText(this@MainActivity, "请用您的指尖盖住摄像头镜头！", Toast.LENGTH_SHORT).show()
//					hua[20] = 0
//				}
//				hua[20]++
//				return
//			} else hua[20] = 10
//			j = 0
//		}
//		if (j < 20) {
//			addY = hua[j].toDouble()
//			j++
//		}
//
//		//移除数据集中旧的点集
//		mDataset.removeSeries(series)
//
//		//判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，长度永远是100
//		var length: Int = series.getItemCount()
//		var bz = 0        //addX = length;
//		if (length > 300) {
//			length = 300
//			bz = 1
//		}
//		addX = length        //将旧的点集中x和y的数值取出来放入backup中，并且将x的值加1，造成曲线向右平移的效果
//		for (i in 0 until length) {
//			xv[i] = series.getX(i) as Int - bz
//			yv[i] = series.getY(i) as Int
//		}
//
//		//点集先清空，为了做成新的点集而准备
//		series.clear()
//		mDataset.addSeries(series)        //将新产生的点首先加入到点集中，然后在循环体中将坐标变换后的一系列点都重新加入到点集中
//		//这里可以试验一下把顺序颠倒过来是什么效果，即先运行循环体，再添加新产生的点
//		series.add(addX, addY)
//		for (k in 0 until length) {
//			series.add(xv[k], yv[k])
//		}        //在数据集中添加新的点集
//		//mDataset.addSeries(series);
//
//		//视图更新，没有这一步，曲线不会呈现动态
//		//如果在非UI主线程中，需要调用postInvalidate()，具体参考api
//		chart.invalidate()
//	} //曲线
//
//	override fun onConfigurationChanged(newConfig: Configuration) {
//		super.onConfigurationChanged(newConfig)
//	}
//
//	public override fun onResume() {
//		super.onResume()
//		wakeLock!!.acquire()
//		camera = Camera.open()
//		startTime = System.currentTimeMillis()
//	}
//
//	public override fun onPause() {
//		super.onPause()
//		wakeLock!!.release()
//		camera!!.setPreviewCallback(null)
//		camera!!.stopPreview()
//		camera!!.release()
//		camera = null
//	}
//
//	companion object {
//		private var gx = 0
//		private var j = 0
//		private var flag = 1.0
//
//		//	private static final String TAG = "HeartRateMonitor";
//		private val processing = AtomicBoolean(false)
//
//		//Android手机预览控件
//		private var preview: SurfaceView? = null
//
//		//预览设置信息
//		private var previewHolder: SurfaceHolder? = null
//
//		//Android手机相机句柄
//		private var camera: Camera? = null
//
//		//private static View image = null;
//		private var text: TextView? = null
//		private var text1: TextView? = null
//		private var text2: TextView? = null
//		private var wakeLock: WakeLock? = null
//		private var averageIndex = 0
//		private const val averageArraySize = 4
//		private val averageArray = IntArray(averageArraySize) //获取当前类型
//
//		//设置默认类型
//		var current = TYPE.GREEN
//			private set
//
//		//心跳下标值
//		private var beatsIndex = 0
//
//		//心跳数组的大小
//		private const val beatsArraySize = 3
//
//		//心跳数组
//		private val beatsArray = IntArray(beatsArraySize)
//
//		//心跳脉冲
//		private var beats = 0.0
//
//		//开始时间
//		private var startTime: Long = 0
//
//		/**
//		 * 相机预览方法
//		 * 这个方法中实现动态更新界面UI的功能，
//		 * 通过获取手机摄像头的参数来实时动态计算平均像素值、脉冲数，从而实时动态计算心率值。
//		 */
//		private val previewCallback = PreviewCallback { data, cam ->
//			if (data == null) throw NullPointerException()
//			val size = cam.parameters.previewSize ?: throw NullPointerException()
//			if (!processing.compareAndSet(false, true)) return@PreviewCallback
//			val width = size.width
//			val height = size.height            //图像处理
//			val imgAvg: Int = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(), height, width)
//			gx = imgAvg
//			text1!!.text = "平均像素值是$imgAvg"            //像素平均值imgAvg,日志
//			//Log.i(TAG, "imgAvg=" + imgAvg);
//			if (imgAvg == 0 || imgAvg == 255) {
//				processing.set(false)
//				return@PreviewCallback
//			}            //计算平均值
//			var averageArrayAvg = 0
//			var averageArrayCnt = 0
//			for (i in averageArray.indices) {
//				if (averageArray[i] > 0) {
//					averageArrayAvg += averageArray[i]
//					averageArrayCnt++
//				}
//			}            //计算平均值
//			val rollingAverage = if (averageArrayCnt > 0) averageArrayAvg / averageArrayCnt else 0
//			var newType = current
//			if (imgAvg < rollingAverage) {
//				newType = TYPE.RED
//				if (newType != current) {
//					beats++
//					flag = 0.0
//					text2!!.text = "脉冲数是" + beats.toString()                    //Log.e(TAG, "BEAT!! beats=" + beats);
//				}
//			} else if (imgAvg > rollingAverage) {
//				newType = TYPE.GREEN
//			}
//			if (averageIndex == averageArraySize) averageIndex = 0
//			averageArray[averageIndex] = imgAvg
//			averageIndex++
//
//			// Transitioned from one state to another to the same
//			if (newType != current) {
//				current = newType                //image.postInvalidate();
//			}            //获取系统结束时间（ms）
//			val endTime = System.currentTimeMillis()
//			val totalTimeInSecs = (endTime - startTime) / 1000.0
//			if (totalTimeInSecs >= 2) {
//				val bps = beats / totalTimeInSecs
//				val dpm = (bps * 60.0).toInt()
//				if (dpm < 30 || dpm > 180 || imgAvg < 200) {                    //获取系统开始时间（ms）
//					startTime = System.currentTimeMillis()                    //beats心跳总数
//					beats = 0.0
//					processing.set(false)
//					return@PreviewCallback
//				}                //Log.e(TAG, "totalTimeInSecs=" + totalTimeInSecs + " beats="+ beats);
//				if (beatsIndex == beatsArraySize) beatsIndex = 0
//				beatsArray[beatsIndex] = dpm
//				beatsIndex++
//				var beatsArrayAvg = 0
//				var beatsArrayCnt = 0
//				for (i in beatsArray.indices) {
//					if (beatsArray[i] > 0) {
//						beatsArrayAvg += beatsArray[i]
//						beatsArrayCnt++
//					}
//				}
//				val beatsAvg = beatsArrayAvg / beatsArrayCnt
//				text!!.text = "您的的心率是" + beatsAvg.toString() + "  zhi:" + beatsArray.size.toString() + "    " + beatsIndex.toString() + "    " + beatsArrayAvg.toString() + "    " + beatsArrayCnt.toString()                //获取系统时间（ms）
//				startTime = System.currentTimeMillis()
//				beats = 0.0
//			}
//			processing.set(false)
//		}
//
//		/**
//		 * 预览回调接口
//		 */
//		private val surfaceCallback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {
//			//创建时调用
//			override fun surfaceCreated(holder: SurfaceHolder) {
//				try {
//					camera!!.setPreviewDisplay(previewHolder)
//					camera!!.setPreviewCallback(previewCallback)
//				} catch (t: Throwable) {
//					Log.e("PreviewDemo-surfaceCallback", "Exception in setPreviewDisplay()", t)
//				}
//			}
//
//			//当预览改变的时候回调此方法
//			override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
//				val parameters = camera!!.parameters
//				parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
//				val size = getSmallestPreviewSize(width, height, parameters)
//				if (size != null) {
//					parameters.setPreviewSize(size.width, size.height)                //				Log.d(TAG, "Using width=" + size.width + " height="	+ size.height);
//				}
//				camera!!.parameters = parameters
//				camera!!.startPreview()
//			}
//
//			//销毁的时候调用
//			override fun surfaceDestroyed(holder: SurfaceHolder) {            // Ignore
//			}
//		}
//
//		/**
//		 * 获取相机最小的预览尺寸
//		 * @param width
//		 * @param height
//		 * @param parameters
//		 * @return
//		 */
//		private fun getSmallestPreviewSize(width: Int, height: Int, parameters: Camera.Parameters): Camera.Size? {
//			var result: Camera.Size? = null
//			for (size in parameters.supportedPreviewSizes) {
//				if (size.width <= width && size.height <= height) {
//					if (result == null) {
//						result = size
//					} else {
//						val resultArea = result.width * result.height
//						val newArea = size.width * size.height
//						if (newArea < resultArea) result = size
//					}
//				}
//			}
//			return result
//		}
//	}
//}