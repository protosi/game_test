predictVAR <- function(df, p, type, ahead, ci)
{
	inVar <- vars::VAR(df, lag.max=p, type=type);
	inPredict<-stats::predict(inVar, n.ahead = ahead, ci=ci);
	return(inPredict);
} 

makeDataFrame <- function(date, value , startDate, endDate, dateFormat, seq )
{
	
    value <- as.numeric(value);
    date <- as.Date(date, dateFormat);
    fullDate <-seq(from=as.Date(startDate, dateFormat), to=as.Date(endDate, dateFormat) , by=seq);
    raw <-data.frame(date=date,value=value);
    result <- data.frame(date=fullDate, value=with(raw, value[match(fullDate, date)]));
    result$value<-zoo::na.locf(result$value, na.rm=F);
    result$value<-zoo::na.fill(result$value, 'extend');
    return(result);
}