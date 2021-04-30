package com.mibe.bm.wi.files

class FilePathServiceImpl : FilePathService {
    override fun getFilePath(filename: String): String {
        return getResourcesPath(filename)
    }

    private fun getResourcesPath(path: String): String = ClassLoader.getSystemResource(path).path
}