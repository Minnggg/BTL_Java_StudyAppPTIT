package com.example.testapi

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptorLichHoc : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
//                .addHeader("Authorization","Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6Ii03MDA0NTA0MTg1NDY0NzM2MDAyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IkIyMURDQ041MzUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL2FjY2Vzc2NvbnRyb2xzZXJ2aWNlLzIwMTAvMDcvY2xhaW1zL2lkZW50aXR5cHJvdmlkZXIiOiJBU1AuTkVUIElkZW50aXR5IiwiQXNwTmV0LklkZW50aXR5LlNlY3VyaXR5U3RhbXAiOiJhODhjMjk5ZS1kMmE5LTRhZjAtYjdhYS02MmZiZjEyMjI4OGYiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJTSU5IVklFTiIsInNlc3Npb24iOiItNDk4MzM0MzA5MjgxODE0ODI0NSIsImR2cGMiOiItNjQyNTg1NTUwNjQ5Mzc1MDU1MSIsIm5hbWUiOiJQaGFuIE5n4buNYyBNaW5oIiwicGFzc3R5cGUiOiIwIiwidWN2IjoiMjI3MzA2NjAzIiwicHJpbmNpcGFsIjoiTWluaFBOLkIyMUNONTM1QHN0dS5wdGl0LmVkdS52biIsIndjZiI6IjAiLCJuYmYiOjE2OTk4MDM1MTIsImV4cCI6MTY5OTgwNTMxMiwiaXNzIjoiZWR1c29mdCIsImF1ZCI6ImFsbCJ9.eaTXD5LK2X-TH_1aka3YDLbXeX8GhvM8U8N36spwGxU")
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "application/json")
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36")
                .build()
        )
    }
}