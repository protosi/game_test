require(vars)
require(MASS)
require(zoo)
require(urca)
getVAR <- function(df, p, type)
{
	inVar <- vars::VAR(df, lag.max=p, type=type);
	return(inVar);
}
makeVARfromVECM <- function(df, ecdet, type, k, season)
{
	inVecm <-urca::ca.jo(df, ecdet=ecdet, type=type, K=k, season=season, spec="longrun");
	r<-0;	
	sm <- summary(inVecm);
	length <- length(sm@cval[,1]);
	
	for(i in seq(1, length))
	{
		if(sm@teststat[i] < sm@cval[i, 1])
		{
			r = length - i;
			break;
		}
	} 
	inVar = vec2var(inVecm, r=r); 
	return(inVar);
}
getVARfromVECM<-function(inVecm)
{
	r<-0;	
	sm <- summary(inVecm);
	length <- length(sm@cval[,1]);
	
	for(i in seq(1, length))
	{
		if(sm@teststat[i] < sm@cval[i, 1])
		{
			r = length - i;
			break;
		}
	} 
	inVar = vec2var(inVecm, r=r); 
	return(inVar);
}

getVECM <- function(df, ecdet, type, k, season)
{
	inVecm <-urca::ca.jo(df, ecdet=ecdet, type=type, K=k, season=season, spec="longrun"); 
	return(inVecm);
}

predictVECM <- function(df, ecdet, type, k, season, ahead, ci)
{
	inVar <- makeVARfromVECM(df, ecdet, type, k, season);
	inPredict<-stats::predict(inVar, n.ahead=ahead, ci=ci);
	return(inPredict);
}
predictVAR <- function(df, p, type, ahead, ci)
{
	inVar <- getVAR(df, p, type);
	inPredict<-stats::predict(inVar, n.ahead=ahead, ci=ci);
	return(inPredict);
} 
makeDataFrame <- function(date, value , startDate, endDate, dateFormat, seq )
{
	
    value <- as.numeric(value);
    date <- as.Date(date, dateFormat);
    fullDate <-seq(from=as.Date(startDate, dateFormat), to=as.Date(endDate, dateFormat) , by=seq);
    raw <-data.frame(date=date,value=value);
    result <- data.frame(date=fullDate, value=with(raw, value[match(fullDate, date)]) , row.names=fullDate);
    result$value<-zoo::na.locf(result$value, na.rm=F);
    result$value<-zoo::na.fill(result$value, 'extend');
    return(result);
}