package com.jecyhw.repository.transform;

import com.jecyhw.document.Picture;

import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 * 根据将抓取保存到mongodb的病虫害document中的图片链接,找出所有的图片链接,相同的链接会进行替换
 * (由于图片下载保存涉及到数据库操作,所以下载保存功能不在此类中实现,提供pictures方法获取该病虫害的图片相关信息,所以调用此方法只是
 * 拷贝了一份DiseasePest,如果传入的对象图片信息未下载入库,将会对图片信息进行整理,去重,然后可通过picture获取所有图片信息,供外部
 * 调用决定在哪下载入库,这里是在DiseasePestRepositoryImpl类中保存入库的)
 * @param <SELF> 被转换的和转换的document,对应的java对象是同一个
 */
public interface PictureTransform<SELF> extends Transform<SELF, SELF> {
    /**
     * 获取所有的图片信息
     * @return
     */
    List<Picture> pictures();
}
