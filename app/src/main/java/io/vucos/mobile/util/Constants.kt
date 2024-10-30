package io.vucos.mobile.util

import com.google.firebase.analytics.FirebaseAnalytics

class Constants {

    object Default {
        val CHANNEL_PING_TIMEOUT: Long = 300000
        val ONESIGNAL_APP_ID = "28b05de6-7252-492a-8958-218a492da2f0"
        val COMMUNICATION_CHANNEL = 3
        var DEVICE_TYPE = 5
        var DEVICE_TYPE_ANDROID_MOBILE = 1
        var DEVICE_TYPE_ANDROID_TV = 5
        val ERROR_DEVICE_LOGOUT = 274912
        val ERROR_NEED_PACKAGE = 25
        val ERROR_REACHED_CONCURRENT_LIMIT = 24
        val ERROR_REACHED_DEVICE_LIMIT = 6102
        val ERROR_REACHED_SAME_DEVICE_LIMIT = 6101
        val LIVE_TV_BOTTOM_TAB_ITEM_COUNT = 3
        val LOG_TAG = "TurksatTVTag"
        val NO_CONNECTION = 876865
        val PLAYER_PARENTAL_CHECK_TIMEOUT: Long = 10000
        val PLAYER_PING_TIMEOUT: Long = 5000
        val PRELOADER_HIDE_TIME = 500
        val PRELOADER_WAIT_TIME = 800
        val REFRESH_TIMEOUT: Long = 30000
        val STREAM_TYPE_LIVE = 1
        val STREAM_TYPE_VOD = 2
        val TOKEN_LIFE_TIME_MILLISECONDS: Long = 2400000
        val TOKEN_LIFE_TIME_SECONDS: Long = 2400
    }

    object AdjustKeys {
        const val LIVETV = "63whcz"
        const val PURCHASE = "ejfnmf"
        const val REGISTRATION = "w0uthv"
        const val TOKEN = "e7pw4nsvy8pa"
        const val VOD = "n81sqo"
    }

    object DateFormat {
        const val DATE = "dd.MM.yyyy"
        const val DATE_DAY_TEXT = "d"
        const val DATE_MONTH_TEXT = "MMM"
        const val DATE_TEXT = "d MMMM EEEE"
        const val DATE_TEXT_MONTH_YEAR = "d MMMM yyyy"
        const val DATE_TIME = "dd.MM.yyyy HH:mm"
        const val DATE_TIME_THUMBNAIL = "yyyy_MM_dd_HH_mm"
        const val EPG_DATE = "yyyy-MM-dd-HH:mm"
        const val TIME_WITH_MINUTES = "HH:mm"
        const val TIME_WITH_SECONDS = "HH:mm:ss"
    }

    object Extras {
        const val ALARM_ALERT_BODY = "tr.Vucos.appALARM_ALERT_BODY"
        const val ALARM_SUBSCRIBER_REMINDER_INFO = "tr.Vucos.appALARM_SUBSCRIBER_REMINDER_INFO"
        const val BASE = "tr.Vucos.app"
        const val BLACKOUT_LIST = "tr.Vucos.appBLACKOUT_LIST"
        const val CALL_CONFIRMATION = "tr.Vucos.appCALL_CONFIRMATION"
        const val CHANNEL_ID = "tr.Vucos.appCHANNEL_ID"
        const val CHANNEL_INFO_LIST = "tr.Vucos.appCHANNEL_INFO_LIST"
        const val CONTENT_TEXT = "tr.Vucos.appCONTENT_TEXT"
        const val EPG_INFO = "tr.Vucos.appEPG_INFO"
        const val IS_NOTIFICATION = "tr.Vucos.appIS_NOTIFICATION"
        const val IS_REPLAY_SCREEN = "tr.Vucos.appIS_REPLAY_SCREEN"
        const val IS_SERIES = "tr.Vucos.appIS_SERIES"
        const val LOGIN_RESPONSE = "tr.Vucos.appLOGIN_RESPONSE"
        const val MSISDN = "tr.Vucos.appMSISDN"
        const val ORGINAL_TITLE = "tr.Vucos.appORGINAL_TITLE"
        const val OTP_DIGIT_NUMBER = "tr.Vucos.appOTP_DIGIT_NUMBER"
        const val OTP_EXPIRY_PERIOD = "tr.Vucos.appOTP_EXPIRY_PERIOD"
        const val PAUSE_TIME = "tr.Vucos.appPAUSE_TIME"
        const val PHONE_NUMBER = "tr.Vucos.appPHONE_NUMBER"
        const val POSTER_IMAGE_URL = "tr.Vucos.appPOSTER_IMAGE_URL"
        const val PROGRAM_ID = "tr.Vucos.appPROGRAM_ID"
        const val SEARCH_INFO = "tr.Vucos.appSEARCH_INFO"
        const val SELECTED_CHANNEL_INFO = "tr.Vucos.appSELECTED_CHANNEL_INFO"
        const val SELECTED_CHANNEL_TIMESTAMP = "tr.Vucos.appSELECTED_CHANNEL_TIMESTAMP"
        const val SHARE_BACKGROUND = "tr.Vucos.appSHARE_BACKGROUND"
        const val STREAM_URL = "tr.Vucos.appSTREAM_URL"
        const val TERMS_FROM_TYPE = "tr.Vucos.appTERMS_FROM_TYPE"
        const val VOD_CONTENT_PARENT_INFO = "tr.Vucos.appVOD_CONTENT_PARENT_INFO"
        const val VOD_CONTENT_PARENT_INFO_LIST = "tr.Vucos.appVOD_CONTENT_PARENT_INFO_LIST"
        const val VOD_DRM_OTT = "tr.Vucos.appVOD_DRM_OTT"
        const val VOD_DRM_SYSTEM_NAME = "tr.Vucos.appVOD_DRM_SYSTEM_NAME"
        const val VOD_DRM_XML = "tr.Vucos.appVOD_DRM_XML"
        const val VOD_HAS_DRM = "tr.Vucos.appVOD_HAS_DRM"
        const val VOD_HAS_MULTI_AUDIO = "tr.Vucos.appVOD_HAS_MULTI_AUDIO"
        const val VOD_ID = "tr.Vucos.appVOD_ID"
        const val VOD_TEXT_TRACKS = "tr.Vucos.appVOD_TEXT_TRACKS"
    }

    object RequestCode {
        const val DEVICE_MANAGEMENT = 41324
    }

    object SSOKeys {
        const val OTP_BASE_ADDRESS = "https://onelogin.Vucos.com.tr/hermes/"
        const val SSO_APP_ID = "Vucos01"
        const val SSO_AUTO_BASE_ADDRESS = "http://onelogin.Vucos.com.tr/"
        const val SSO_BASE_ADDRESS = "https://onelogin.Vucos.com.tr/"
        const val SSO_CHANGE_PASSWORD = "changePassword"
        const val SSO_GENERATE_PASSWORD = "generatePassword"
        const val SSO_GET_TOKEN = "https://onelogin.Vucos.com.tr/getToken"
        const val SSO_OTP_PARAMETERS_KEY = "otpParameters"
        const val SSO_REMOVE_TOKEN = "removeToken"
        const val SSO_RESPONSE_KEY = "response"
        const val SSO_SUBSCRIBER_KEY = "subscriberInfo"
        const val SSO_TOKEN_KEY = "token"
        const val SSO_VALIDATE_TOKEN = "validateToken"
    }

    object SharedPreferenceKeys {
        const val ALL_EPG_RESPONSE_DATA = "tr.Vucos.appALL_EPG_RESPONSE_DATA"
        const val ALL_EPG_RESPONSE_TIME = "tr.Vucos.appALL_EPG_RESPONSE_TIME"
        const val BASE = "tr.Vucos.app"
        const val CHANNELS_LAST_UPDATE_TIMESTAMP = "tr.Vucos.appCHANNELS_LAST_UPDATE_TIMESTAMP"
        const val DEVICE_ID = "tr.Vucos.appDEVICE_ID"
        const val HOME_SCREEN_CHANNEL_CONTINUE = "tr.Vucos.appHOME_SCREEN_CHANNEL_CONTINUE"
        const val IS_TUTORIAL_DISPLAYED = "IS_TUTORIAL_DISPLAYED"
        const val LAST_CHANNEL_ID = "tr.Vucos.appLAST_CHANNEL_ID"
        const val LOGIN_RESPONSE = "tr.Vucos.appLOGIN_RESPONSE"
        const val LOGIN_TIME = "tr.Vucos.appLOGIN_TIME"
        const val LOGOUT_TIME = "tr.Vucos.appLOGOUT_TIME"
        const val MANUALLY_ADDED_HOME_CATEGORY_UID = "manually-added-home-category"
        const val NEW_EDIT_CHANNEL_LIST = "NEW_EDIT_CHANNEL_LIST"
        const val NEW_EDIT_CHANNEL_LIST_IS_FAVORITE = "NEW_EDIT_CHANNEL_LIST_IS_FAVORITE"
        const val NOTIFICATION_ID = "tr.Vucos.appNOTIFICATION_ID"
        const val PASS = "tr.Vucos.appPASS"
        const val PENDING_EVENTS = "tr.Vucos.appPENDING_EVENTS"
        const val PENDING_REQUEST = "tr.Vucos.appPENDING_REQUEST"
        const val PERMISSION_LOCATION = "tr.Vucos.appPERMISSION_LOCATION"
        const val PIN_CHANNEL_LIST = "PIN_CHANNEL_LIST"
        const val PREFERRED_APP_LANGUAGE = "tr.Vucos.appPREFERRED_APP_LANGUAGE"
        const val PREFERRED_AUDIO_LANGUAGE = "tr.Vucos.appPREFERRED_AUDIO_LANGUAGE"
        const val PREFERRED_SUBTITLE_LANGUAGE = "tr.Vucos.appPREFERRED_SUBTITLE_LANGUAGE"
        const val TOKEN = "tr.Vucos.appTOKEN"
        const val UNPIN_CHANNEL_LIST = "UNPIN_CHANNEL_LIST"
        const val USERNAME = "tr.Vucos.appUSERNAME"
        const val USER_TOKEN_RESPONSE = "tr.Vucos.appUSER_TOKEN_TOKEN"
        const val USER_TOKEN_RESPONSE_TIMESTAMP = "tr.Vucos.appUSER_TOKEN_TOKEN_TIMESTAMP"
        const val USER_TOKEN_WITH_KEY_RESPONSE = "tr.Vucos.appUSER_TOKEN_WITH_KEY"
        const val USER_TOKEN_WITH_KEY_RESPONSE_TIMESTAMP =
            "tr.Vucos.appUSER_TOKEN_WITH_KEY_TIMESTAMP"
        const val VUCOS_TOKEN = "tr.Vucos.appVUCOS_TOKEN"
    }

    object Statistics {
        const val ACCOUNT_DETAIL = "ACCOUNT_DETAIL"
        const val CHANNEL_DETAIL = "CHANNEL_DETAIL"
        const val CHANNEL_EPG_GUIDE = "CHANNEL_EPG_GUIDE"
        const val CHANNEL_LIST = "CHANNEL_LIST"
        const val CHANNEL_PLAYER = "CHANNEL_PLAYER"
        const val HOME = "HOME"
        const val OTHER = "OTHER"
        const val VOD_CATEGORY = "VOD_CATEGORY"
        const val VOD_DETAIL = "VOD_DETAIL"
        const val VOD_PLAYER = "VOD_PLAYER"
    }

    object WebServiceAddresses {
        var API_KEY = ""
        var BASE = "https://core-api.vucos.io/"
        var ROOT = BASE + "api/"
        var FAQS = ROOT + "catalog/faq"
        var VUCOS_SUBSCRIBER = ROOT + "Subscriber"
        var VUCOS_ADD_CATALOG_PREFERENCES = ROOT + "catalog/add-catalog-preferences"
        var VUCOS_ADD_CHANNEL_PARENTAL_CONTROLS = ROOT + "parental-controls/add-channel"
        var VUCOS_ADD_EPG_REMINDER = ROOT + "reminders/add-epg"
        var VUCOS_ADD_FAVORITE_CHANNEL = ROOT + "favorites/add-channel"
        var VUCOS_ADD_FAVORITE_VOD = ROOT + "favorites/add-vod"
        var VUCOS_ADD_GENRES_PARENTAL_CONTROLS = ROOT + "parental-controls/add-genre"
        var VUCOS_ADD_ITEM_TO_PLAYLIST = ROOT + "playlists/add-item"
        var VUCOS_ADD_PROFILE_TIME_RANGE = ROOT + "profiles/add-timerange"
        var VUCOS_ADD_REMOVE_FAVORITE_VOD = ROOT + "favorites/toggle-vod"
        var VUCOS_ADD_SUBSCRIBER_PROFILE = ROOT + "profiles/add"
        var VUCOS_ADD_VOD_PARENTAL_CONTROLS = ROOT + "parental-controls/add-vod"
        var VUCOS_ADD_VOD_REMINDER = ROOT + "reminders/add-vod"
        var VUCOS_APPROVE_CONTRACTS = ROOT + "contracts/approve"
        var VUCOS_APP_VERSION_LIST = ROOT + "app/versions"
        var VUCOS_CAST_CATALOG = ROOT + "catalog/cast"
        var VUCOS_CHANGE_PASSWORD_WITH_OTP = VUCOS_SUBSCRIBER + "/password-recovery"
        var VUCOS_CHANNEL = ROOT + "Channel/"
        var VUCOS_CHANNELS = ROOT + "channels/all"
        var VUCOS_CHANNELS_FINGERPRINT = ROOT + "channels/finger-print"
        var VUCOS_CHANNELS_RECENT_ADDITIONS = ROOT + "channels/recent-additions"
        var VUCOS_CHANNELS_SUMMARY = ROOT + "channels/summary"
        var VUCOS_CHECK_DEVICE_STATUS = ROOT + "device/check-device-status"
        var VUCOS_CONTENTS = ROOT + "Content"
        var VUCOS_CREATE_PLAYLIST = ROOT + "playlists/create"
        var VUCOS_DEFAULT_PLAYLIST_DETAILS = ROOT + "playlists/default"
        var VUCOS_DELETE_NOTIFICATIONS = ROOT + "notifications/delete"
        var VUCOS_DELETE_PLAYLIST = ROOT + "playlists/remove"
        var VUCOS_DEVICES_CHECK_CONCURRENT_USAGE = ROOT + "devices/CheckConcurrentUsage"
        var VUCOS_EPG_DETAIL = ROOT + "epg/detail"
        var VUCOS_EPG_REACTIONS = ROOT + "reactions/epgs"
        var VUCOS_EPG_REMINDERS_LIST = ROOT + "reminders/epgs"
        var VUCOS_FAVORITE_CHANNEL_LIST = ROOT + "favorites/channels"
        var VUCOS_FAVORITE_VOD_LIST = ROOT + "favorites/vods"
        var VUCOS_GET_ALL_GENRES_PARENTAL_CONTROLS =
            ROOT + "parental-controls/search-genres?keyword=a"
        var VUCOS_GET_ALL_RESOURCES = ROOT + "resources"
        var VUCOS_GET_CATALOG_PREFERENCES = ROOT + "catalog/catalog-preferences"
        var VUCOS_GET_CHANNEL_PARENTAL_CONTROLS = ROOT + "parental-controls/channels"
        var VUCOS_GET_CHANNEL_PERMISSIONS = ROOT + "profiles/channel-permissions"
        var VUCOS_GET_GENRES_PARENTAL_CONTROLS = ROOT + "parental-controls/genres"
        var VUCOS_GET_PARENTAL_CONTROLS = ROOT + "parental-controls"
        var VUCOS_GET_PROFILE_AVATARS = ROOT + "resources/avatars"
        var VUCOS_GET_PROFILE_PERMISSIONS = ROOT + "profiles/permissions"
        var VUCOS_GET_PROFILE_PREFERENCES = ROOT + "profiles/preferences"
        var VUCOS_GET_PROFILE_TIME_RANGES = ROOT + "profiles/timeranges"
        var VUCOS_GET_RECOMMENDATION_FOR_USER = ROOT + "recommendation/for-user"
        var VUCOS_GET_RECOMMENDATION_FOR_VOD = ROOT + "recommendation/for-vod"
        var VUCOS_GET_STARTUP_OPTIONS = ROOT + "profiles/profile-startup-options"
        var VUCOS_GET_SUBSCRIBER = ROOT + "subscriber"
        var VUCOS_GET_SUBSCRIBER_PROFILES = ROOT + "profiles"
        var VUCOS_GET_VOD_PARENTAL_CONTROLS = ROOT + "parental-controls/vods"
        var VUCOS_HOME = ROOT + "catalog/home"
        var VUCOS_HOME_CATALOG_CATEGORY = ROOT + "catalog/category-contents"
        var VUCOS_HOME_CATALOG_NAVIGATION = ROOT + "catalog/navigation-categories"
        var VUCOS_KICK_DEVICE = ROOT + "auth/logout-device"
        var VUCOS_LIST_CONTRACTS = ROOT + "contracts/list"
        var VUCOS_LIST_EPG = ROOT + "epg/list"
        var VUCOS_LIST_RECORDED_EPG = ROOT + "npvr/records"
        var VUCOS_LIST_SUBSCRIPTIONS = ROOT + "subscriptions"
        var VUCOS_LIVE_FIRST_PLAY = ROOT + "statistics/channel-first-play"
        var VUCOS_LIVE_WATCH = ROOT + "statistics/channel-watch"
        var VUCOS_LOGIN = ROOT + "auth/login"
        var VUCOS_LOGOUT_DEVICE = ROOT + "subscriber/deactivate-device"
        var VUCOS_MESSAGES = ROOT + "messages"
        var VUCOS_NOTIFICATIONS = ROOT + "notifications"
        var VUCOS_NOTIFICATION_PREFERENCES = VUCOS_SUBSCRIBER + "/notification-preferences"
        var VUCOS_OFFERS = ROOT + "offers"
        var VUCOS_OPERATORS = ROOT + "resources/operators"
        var VUCOS_PAYMENT_CHARGE = ROOT + "PaymentCharge"
        var VUCOS_PENDING_CONTRACTS = ROOT + "contracts"
        var VUCOS_PLAYLIST_DETAILS = ROOT + "playlists/get"
        var VUCOS_PLAYLIST_SUMMARY = ROOT + "playlists/summary"
        var VUCOS_POPULAR_SEARCH = ROOT + "Search/Popular"
        var VUCOS_PURCHASE_HISTORY = ROOT + "history/purchases"
        var VUCOS_PURCHASE_VOD = ROOT + "vod/purchase-vod"
        var VUCOS_READ_NOTIFICATIONS = ROOT + "notifications/read"
        var VUCOS_RECENT_SEARCH = ROOT + "Search/Recent"
        var VUCOS_RECOMMENDATION = ROOT + "Recommendation/"
        var VUCOS_RECOMMENDATION_VOD = VUCOS_RECOMMENDATION + "VoD"
        var VUCOS_RECOMMENDATION_VODForVOD = VUCOS_RECOMMENDATION + "VoDForVod"
        var VUCOS_RECORD_EPG = ROOT + "npvr/record"
        var VUCOS_REGIONS = ROOT + "resources/regions"
        var VUCOS_REGISTER = ROOT + "auth/register"
        var VUCOS_REMOVE_CHANNEL_PARENTAL_CONTROLS = ROOT + "parental-controls/remove-channel"
        var VUCOS_REMOVE_EPG_REMINDER = ROOT + "reminders/remove-epg"
        var VUCOS_REMOVE_FAVORITE = ROOT + "favorites/remove"
        var VUCOS_REMOVE_GENRES_PARENTAL_CONTROLS = ROOT + "parental-controls/remove-genre"
        var VUCOS_REMOVE_ITEM_FROM_PLAYLIST = ROOT + "playlists/remove-item"
        var VUCOS_REMOVE_MESSAGES = ROOT + "messages/remove"
        var VUCOS_REMOVE_PROFILE_TIME_RANGE = ROOT + "profiles/remove-timerange"
        var VUCOS_REMOVE_RECORDED_EPG = ROOT + "npvr/remove"
        var VUCOS_REMOVE_SUBSCRIBER_PROFILE = ROOT + "profiles/remove"
        var VUCOS_REMOVE_VOD_HISTORY = ROOT + "history/remove-vod"
        var VUCOS_REMOVE_VOD_PARENTAL_CONTROLS = ROOT + "parental-controls/remove-vod"
        var VUCOS_REMOVE_VOD_REMINDER = ROOT + "reminders/remove-vod"
        var VUCOS_RENT_VOD = ROOT + "vod/rent-vod"
        var VUCOS_REQUEST_PASSWORD_OTP = VUCOS_SUBSCRIBER + "/request-password-recovery"
        var VUCOS_SCREEN_ACTIVITY = ROOT + "statistics/add-activity"
        var VUCOS_SEARCH = ROOT + FirebaseAnalytics.Event.SEARCH
        var VUCOS_SEARCH_AUTOCOMPLETE = ROOT + FirebaseAnalytics.Event.SEARCH
        var VUCOS_SELECT_PROFILE = ROOT + "auth/select-profile"
        var VUCOS_SIMPLE_VOD_LIST = ROOT + "/vodcontent/SimpleVodList"
        var VUCOS_SORT_FAVORITE_CHANNELS = ROOT + "favorites/sort-channels"
        var VUCOS_SORT_FAVORITE_VODS = ROOT + "favorites/sort-vods"
        var VUCOS_SORT_PLAYLIST = ROOT + "playlists/reorder"
        var VUCOS_STATISTIC = ROOT + "Statistic"

        var VUCOS_SUBSCRIBER_DEVICES = ROOT + "subscriber/devices"
        var VUCOS_THEMES = ROOT + "resources/themes"
        var VUCOS_THEME_GROUPS = ROOT + "resources/theme-groups"
        var VUCOS_TOGGLE_EPG_REMINDER = ROOT + "reminders/toggle-epg"
        var VUCOS_TOGGLE_VOD_REMINDER = ROOT + "reminders/toggle-vod"
        var VUCOS_TOGGLE_WATCHED = ROOT + "vod/toggle-watched"
        var VUCOS_TOP_MENU = ROOT + "menu/navigation"
        var VUCOS_UPDATE_CHANNEL_PERMISSIONS = ROOT + "profiles/update-channel-permissions"
        var VUCOS_UPDATE_EPG_REACTION = ROOT + "reactions/update-epg"
        var VUCOS_UPDATE_MESSAGES = ROOT + "messages/update"
        var VUCOS_UPDATE_NOTIFICATION_PREFERENCE =
            VUCOS_SUBSCRIBER + "/update-notification-preference"
        var VUCOS_UPDATE_PARENTAL_CONTROLS = ROOT + "parental-controls/update"
        var VUCOS_UPDATE_PARENTAL_CONTROLS_PIN = ROOT + "parental-controls/update-pin"
        var VUCOS_UPDATE_PROFILE_PERMISSIONS = ROOT + "profiles/update-permissions"
        var VUCOS_UPDATE_PROFILE_PREFERENCES = ROOT + "profiles/update-preferences"
        var VUCOS_UPDATE_STARTUP_OPTIONS = ROOT + "profiles/update-profile-startup-options"
        var VUCOS_UPDATE_SUBSCRIBER = ROOT + "subscriber/update"
        var VUCOS_UPDATE_SUBSCRIBER_PARENTAL_CONTROLS =
            VUCOS_SUBSCRIBER + "/update-parental-controls"
        var VUCOS_UPDATE_SUBSCRIBER_PROFILE = ROOT + "profiles/update"
        var VUCOS_UPDATE_SUBSCRIBER_PROFILE_LANGUAGE = ROOT + "profiles/set-language"
        var VUCOS_UPDATE_THEME_PREFERENCES = ROOT + "profiles/update-theme-preferences"
        var VUCOS_UPDATE_VOD_REACTION = ROOT + "reactions/update-vod"
        var VUCOS_VALIDATE = ROOT + "app/validate"
        var VUCOS_VALIDATE_PARENTAL_CONTROLS_PIN = ROOT + "parental-controls/validate-pin"
        var VUCOS_VALIDATE_PARENTAL_CONTROLS_PROFILE_PIN =
            ROOT + "parental-controls/validate-profile-pin"
        var VUCOS_VALIDATE_PASSWORD = ROOT + "auth/validate-password"
        var VUCOS_VOD_CATEGORY = ROOT + "/vodcontent/GetByCategory"
        var VUCOS_VOD_CONTENT = ROOT + "VodContent"
        var VUCOS_VOD_DETAILS = ROOT + "vod/detail"
        var VUCOS_VOD_FIRST_PLAY = ROOT + "statistics/vod-first-play"
        var VUCOS_VOD_HISTORY = ROOT + "history/vods"
        var VUCOS_VOD_REACTIONS = ROOT + "reactions/vods"
        var VUCOS_VOD_REMINDERS_LIST = ROOT + "reminders/vods"
        var VUCOS_VOD_WATCH = ROOT + "statistics/vod-watch"
    }
}