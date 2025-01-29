package octoping.ticketing.domain.exception

class NotFoundException(
    message: String = "찾을 수 없습니다",
) : DomainException(message)
