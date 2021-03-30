package saba.qazi.meetingrooms.meetingroomdetail.data

data class MeetingRoomDetail(
    val capacity: Int,
    val equipment: List<Equipment>,
    val facilities: List<Facility>,
    val features: List<Feature>,
    val heroImageUrl: String,
    val key: String,
    val location: Location,
    val name: String,
    val services: List<Service>
)