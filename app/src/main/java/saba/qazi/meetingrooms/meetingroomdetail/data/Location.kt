package saba.qazi.meetingrooms.meetingroomdetail.data

data class Location(
    val building: Building,
    val floor: Floor,
    val region: Region,
    val site: Site
)