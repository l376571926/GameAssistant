package com.qianfeng.gameassistant.other.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Li Yiwei
 *
 * @date : 2016/1/12.
 */
public class ZhuShouHttpUtil
{

    private static final String tag = "ZhuShouHttpUtil";

    /**
     * 进行GET请求
     *
     * @param httpUrl 请求地址
     * @return 请求结果
     */
    public static Object doGet(String httpUrl)
    {
        if (TextUtils.isEmpty(httpUrl))
        {
            throw new NullPointerException("get 请求 url 不能为空!!!");
        }
        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(httpUrl);
            // 打开一个连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            conn.setRequestMethod("GET");
            // 设置连接超时时间
            conn.setConnectTimeout(5000);
            // 设置读取超时时间
            conn.setReadTimeout(5000);
            // 设置可读操作
            conn.setDoInput(true);
            // 进行连接
            conn.connect();
            // 获取返回码
            int responseCode = conn.getResponseCode();
            // 如果返回码是200,表示请求成功,方可作后面的读取操作
            if (responseCode == HttpURLConnection.HTTP_OK)
            {

                inputStream = conn.getInputStream();
                reader = new InputStreamReader(inputStream);

                bufferedReader = new BufferedReader(reader);
                StringBuffer resultBuffer = new StringBuffer();
                String line = null;
                // 循环读取每一行,判断是否为空,读完为止
                while ((line = bufferedReader.readLine()) != null)
                {
                    // 把每一行的结果连接起来
                    resultBuffer.append(line);
                }
                String result = resultBuffer.toString();
                LogUtil.e("ZhuShouHttpUtil", "GET请求成功 result = " + result);
                // 返回请求的结果
                return result;
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            // 请求结束后关闭流
            try
            {
                // 如果没有联网的情况下,这些流都为null,要避免空指针异常
                if (inputStream == null)
                {
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        LogUtil.e("ZhuShouHttpUtil", "GET请求失败 ");
        return null;
    }

    /**
     * 进行POST请求
     *
     * @param httpUrl 请求地址
     * @param params  参数
     * @return 请求结果
     */
    public static Object doPost(String httpUrl, Map<String, String> params)
    {

        if (TextUtils.isEmpty(httpUrl))
        {
            throw new NullPointerException("post请求 url 不能为空!!!");
        }
        if (params == null)
        {
            throw new NullPointerException("post请求 参数 不能为空!!!");
        }
        // 参数处理 把参数转换成字符串
        // platform=2&ver=v1.2.2
        // 先map转化为set
        Set<Map.Entry<String, String>> entries = params.entrySet();
        // 从set里面获取器
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        StringBuffer paramsBuffer = new StringBuffer();
        // 进行迭代
        while (iterator.hasNext())
        {
            // 获取entry
            Map.Entry<String, String> entry = iterator.next();
            // 获取entry的key
            String key = entry.getKey();
            paramsBuffer.append(key);

            paramsBuffer.append("=");

            // 获取entry的value进行连接
            String value = entry.getValue();
            paramsBuffer.append(value);

            paramsBuffer.append("&");
        }
        String paramsString = paramsBuffer.toString();
        // paramsString
        paramsString = paramsBuffer.substring(0, paramsString.length() - 1);

        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try
        {
            // 设置一个url
            URL url = new URL(httpUrl);
            // 打开一个连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置连接的属性
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            // 开始连接
            conn.connect();

            // 获取输出流
            outputStream = conn.getOutputStream();
            // 向服务器写入参数
            outputStream.write(paramsString.getBytes());
            outputStream.flush();

            int responseCode = conn.getResponseCode();
            // 如果返回码==200,表示请求连接成功
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                // 获取输入流
                inputStream = conn.getInputStream();
                reader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(reader);

                StringBuffer resultBuffer = new StringBuffer();
                String line = null;
                // 循环读取每一行,判断是否为空,读完为止
                while ((line = bufferedReader.readLine()) != null)
                {
                    // 把每一行的结果连接起来
                    resultBuffer.append(line);
                }
                String result = resultBuffer.toString();
                LogUtil.e("tag", "POST请求成功");
                // 返回请求的结果
                return result;
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            // 请求结束后关闭流
            try
            {
                // 如果没有联网的情况下,这些流都为null,要避免空指针异常
                if (inputStream == null)
                {
                    LogUtil.e("tag", "inputStream == null POST请求失败 ");
                    return null;
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
                outputStream.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        LogUtil.e("tag", "POST请求失败 ");

        return null;
    }

    /**
     * @param httpUrl
     * @return
     */
    public static Bitmap downLoadBitmap(String httpUrl)
    {
        InputStream inputStream = null;
        try
        {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                LogUtil.e("", "图片下载成功!!!");
                return bitmap;
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                inputStream.close();
            } catch (IOException e)
            {
                LogUtil.e(tag, "关流失败 ");
                e.printStackTrace();
            }
        }
        LogUtil.e(tag, "网络连接出错,POST请求失败!!!");
        return null;
    }

    /**
     * 下载文件操作
     *
     * @param dir      存放目录
     * @param fileName 重命名
     * @param src
     * @return
     */
    public static File downLoadFile(File dir, String fileName, String src, ZhuShouTask.UpgradeListener upgradeProgress)
    {
        // 如果文件夹不存在,那么创建文件夹,如果这里的判断条件为dir.exists()而不是!dir.exists(),后面fos会为空并抛出异常
        if (!dir.exists())
        {
            dir.mkdirs();
        }

        File apk = new File(dir, fileName);
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try
        {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                inputStream = conn.getInputStream();
                fos = new FileOutputStream(apk);
                byte[] buff = new byte[256];
                int read = 0;

                // 获取文件的总大小,也就是字节数
                long total = conn.getContentLength();
                LogUtil.e("tag", "total = " + total);
                long down = 0;

                while (true)
                {
                    // 每次读取256个字节
                    read = inputStream.read(buff);
                    // 计算已下载字节数
                    down += read;

                    // 计算下载进度
                    int per = (int) (down * 100 / total);

                    // 如果read=-1,表示文件下载完毕了
                    if (read == -1)
                    {
                        per = 100;
                        upgradeProgress.showProgress(per);
                        break;

                    } else
                    {
                        fos.write(buff, 0, read);
                        fos.flush();

                        // 预防计算的结果超过100
                        if (per > 100)
                        {
                            per = 100;
                        }
                        // 实时更新进度
                        upgradeProgress.showProgress(per);

                        LogUtil.e("tag", "down = " + down + ", per = " + per);
                    }
                }
                LogUtil.e("tag", "下载成功!!!  apk:" + apk.toString());
                return apk;
            }

        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (inputStream == null || fos == null)
            {
                LogUtil.e("tag", "inputStream == null || fos == null 请求失败");
                try
                {
                    throw new Exception("inputStream == null || fos == null post请求失败!!!");
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            } else
            {
                try
                {
                    inputStream.close();
                    fos.close();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        LogUtil.e("tag", "网络连接出错,POST请求失败!!!");
        return null;
    }
}