package com.dyrent.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. Author: Jerry.hu Create: Jerry.hu (14-3-12 15:28)
 * Description: To change this template use File | Settings | File Templates.
 */
public class BarCodeUtil {

	private static final int LARGE = 200;

	/**
	 * 生成图片的默认宽度像素
	 */
	private static final int DEFAULT_WIDTH = LARGE;

	/**
	 * 生成图片的默认高度像素
	 */
	private static final int DEFAULT_HEIGHT = LARGE;

	/**
	 * 参数map容量
	 */
	private static final int CAPACITY_OF_HINTS = 3;

	/**
	 * 根据提供的字符串内容生成二维码图片<br />
	 * 编码方式：QR_CODE<br />
	 * 宽度：300px，高度300px<br />
	 * 字符集：UTF-8<br />
	 * 容错等级：L
	 *
	 * @param contents
	 *            二维码内字符串内容
	 * @return 二维码图片
	 * @throws WriterException
	 *             生成二维出错
	 * @throws IllegalArgumentException
	 *             参数为null或空字符串
	 */
	public static BufferedImage encode(String contents) throws WriterException,
			IllegalArgumentException {
		return encode(BarcodeFormat.QR_CODE, contents);
	}

	/**
	 * 根据提供的字符串内容生成二维码图片<br />
	 * 宽度：300px，高度300px<br />
	 * 字符集：UTF-8<br />
	 * 容错等级：L
	 *
	 * @param format
	 *            编码方式
	 * @param contents
	 *            二维码内字符串内容
	 * @return 二维码图片
	 * @throws WriterException
	 *             生成二维出错
	 * @throws IllegalArgumentException
	 *             参数为null或空字符串
	 */
	public static BufferedImage encode(BarcodeFormat format, String contents)
			throws WriterException, IllegalArgumentException {
		return encode(format, contents, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 根据提供的字符串内容、宽度、高度生成二维码图片<br />
	 * 字符集：UTF-8<br />
	 * 容错等级：L
	 *
	 * @param format
	 *            编码方式
	 * @param contents
	 *            二维码字符串内容
	 * @param width
	 *            二维码像素宽度
	 * @param height
	 *            二维码像素高度
	 * @throws WriterException
	 *             生成二维出错
	 * @throws IllegalArgumentException
	 *             参数为null或空字符串或像素设置错误
	 */
	public static BufferedImage encode(BarcodeFormat format, String contents,
			int width, int height) throws WriterException,
			IllegalArgumentException {
		if (contents == null || "".equals(contents)) {
			throw new IllegalArgumentException("要生成二维码的内容为空");
		}
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("要生成二维码的像素设置错误");
		}
		return genImage(contents, format, width, height,
				ErrorCorrectionLevel.L, "UTF-8");
	}

	/**
	 * @param image
	 * @return String
	 * @throws NotFoundException
	 */
	public static String decode(BufferedImage image) throws NotFoundException {
		if (image == null) {
			throw new IllegalArgumentException("要解码的图片为空");
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap, null);
		return result.getText();
	}

	/**
	 * 根据提供的内容生成图片
	 */
	private static BufferedImage genImage(String contents,
			BarcodeFormat format, int width, int height,
			ErrorCorrectionLevel errorCorrectionLevel, String characterSet)
			throws WriterException {
		Map<EncodeHintType, Object> hints = new HashMap<>(CAPACITY_OF_HINTS);
		hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
		hints.put(EncodeHintType.CHARACTER_SET, characterSet);
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format,
				width, height, hints);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

	/**
	 * 生成二维码图片<br>
	 *
	 * @param format
	 *            图片格式
	 * @param file
	 *            生成二维码图片位置
	 * @throws IOException
	 */
	public static void writeToFile(BarcodeFormat format, String contents,
			int width, int height, File file) throws WriterException,
			IOException {
		if (!file.exists()) {
			file.mkdirs();
		}
		BufferedImage image = encode(format, contents, width, height);
		ImageIO.write(image, "png", file);
	}
}